package com.yffd.bcap.common.code.generator.sql;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月18日 下午5:50:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DeleteMybatisSqlBuilder extends MybatisSqlBuilder {

	public static final String SQL_ID_DELETE = "deleteBy";
	public static final String DEF_PARAM_TYPE = "java.utils.Map";
	public static final List<String> DEF_SKIP_PROPS = new ArrayList<String>();
	
	static {
		DEF_SKIP_PROPS.add("version");
		DEF_SKIP_PROPS.add("delFlag");
		DEF_SKIP_PROPS.add("createBy");
		DEF_SKIP_PROPS.add("createTime");
		DEF_SKIP_PROPS.add("updateBy");
		DEF_SKIP_PROPS.add("updateTime");
	}
	
	public String buildSqlDelete(Class<?> entityClazz, Class<?> baseEntityClazz) {
		String tableAlias = null;
		String paramAlias = DEF_PARAM_ALIAS;
		
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 删除 -->").append("\r\n");
		sb.append(String.format("<delete id=\"%s\" parameterType=\"%s\">", SQL_ID_DELETE, DEF_PARAM_TYPE)).append("\r\n");
		sb.append("\t").append(String.format("delete from <include refid=\"%s\" />", DEF_SQL_ID_TABLE_NAME)).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		String whereContent = this.buildWhereConditions(tableAlias, paramAlias, entityClazz, baseEntityClazz);
		buildChoose(paramAlias, DEF_PARAM_MAP_ALIAS, whereContent, sb);
		sb.append("\t").append("</where>").append("\r\n");
		
		sb.append("</delete>").append("\r\n");
		return sb.toString();
	}

	@Override
	protected String buildWhereConditions(String tableAlias, String paramAlias, Class<?> entityClazz, Class<?> baseEntityClazz) {
		List<TableColumn> equalColums = new ArrayList<>();
		List<TableColumn> likeColums = new ArrayList<>();
		List<TableColumn> inColums = new ArrayList<>();
		
		List<TableColumn> columns = this.prop2column(entityClazz, baseEntityClazz);
		for (TableColumn tc : columns) {
			String propName = tc.getPropName();
			if (DEF_SKIP_PROPS.contains(propName)) continue;
			// id 和 code 可用 in条件
			if (propName.matches(DEF_REGEX_IN_ID) || propName.matches(DEF_REGEX_IN_CODE)) {
				inColums.add(tc);
			}
			equalColums.add(tc);
		}
		
		String paramMapAlias = DEF_PARAM_MAP_ALIAS;
		
		List<String> equalStringList = this.buildIfEqual(equalColums, tableAlias, paramAlias);
		List<String> likeStringList = this.buildIfLike(likeColums, tableAlias, paramAlias);
		List<String> inStringList = this.buildIfIn(inColums, tableAlias, paramMapAlias);
		String conditions = this.buildConditions(paramAlias, paramMapAlias, equalStringList, likeStringList, inStringList);
		return conditions;
	}
	
	protected void buildChoose(String paramAlias, String paramMapAlias, String whereContent, StringBuilder sb) {
		sb.append("\t").append("<choose>").append("\r\n");
		sb.append("\t").append(String.format("<when test=\"%s != null or %s != null\">", paramAlias, paramMapAlias)).append("\r\n");
		sb.append(this.linesFmt(whereContent, "\t", ""));
		sb.append("\t").append("</when>").append("\r\n");
		sb.append("\t").append("<otherwise>").append("\r\n");
		sb.append("\t").append("<!-- 防止没有参数传递，导致全部删除 -->").append("\r\n");
		sb.append("\t").append("<if test=\"propsMap != null and propsMap.delAll != 'delAll'\"> and 1=2 </if>").append("\r\n");
		sb.append("\t").append("</otherwise>").append("\r\n");
		sb.append("\t").append("</choose>").append("\r\n");
	}
	
}

