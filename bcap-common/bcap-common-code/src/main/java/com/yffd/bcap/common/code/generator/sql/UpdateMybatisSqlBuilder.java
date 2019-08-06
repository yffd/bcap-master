package com.yffd.bcap.common.code.generator.sql;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月20日 下午1:48:39 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UpdateMybatisSqlBuilder extends MybatisSqlBuilder {
	public static final String SQL_ID_UPDATEBY = "updateBy";
	public static final String DEF_PARAM_TYPE = "java.utils.Map";
	
	public String buildSqlUpdate(Class<?> entityClazz, Class<?> baseEntityClazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 更新 -->").append("\r\n");
		sb.append(String.format("<update id=\"%s\" parameterType=\"java.utils.Map\">", SQL_ID_UPDATEBY)).append("\r\n");
		sb.append("\t").append(String.format("update <include refid=\"%s\" />", DEF_SQL_ID_TABLE_NAME)).append("\r\n");
		
		sb.append("\t").append("<set>").append("\r\n");
		sb.append(this.linesFmt(this.buildSet(entityClazz, baseEntityClazz), "\t", "\r\n"));
		sb.append("\t").append("</set>").append("\r\n");
		
		sb.append("\t").append("<where>").append("\r\n");
		String tableAlias = null;
		String paramAlias = "entityOld";
		String whereContent = this.buildWhereConditions(tableAlias, paramAlias, entityClazz, baseEntityClazz);
		buildChoose(paramAlias, DEF_PARAM_MAP_ALIAS, whereContent, sb);
		sb.append("\t").append("</where>").append("\r\n");
		
		sb.append("</update>").append("\r\n");
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
	
	private void buildChoose(String paramAlias, String paramMapAlias, String whereContent, StringBuilder sb) {
		sb.append("\t").append("<choose>").append("\r\n");
		sb.append("\t").append(String.format("<when test=\"%s != null or %s != null\">", paramAlias, paramMapAlias)).append("\r\n");
		sb.append(this.linesFmt(whereContent, "\t", ""));
		sb.append("\t").append("</when>").append("\r\n");
		sb.append("\t").append("<otherwise>").append("\r\n");
		sb.append("\t").append("<!-- 防止没有参数传递，导致全部更新 -->").append("\r\n");
		sb.append("\t").append("and 1=2").append("\r\n");
		sb.append("\t").append("</otherwise>").append("\r\n");
		sb.append("\t").append("</choose>").append("\r\n");
	}
	
	private String buildSet(Class<?> entityClazz, Class<?> baseEntityClazz) {
		StringBuilder sb = new StringBuilder();
		List<TableColumn> tableColumns = this.prop2column(entityClazz, baseEntityClazz);
		sb.append(String.format("<if test=\"%s != null\">", DEF_PARAM_ALIAS)).append("\r\n");
		for (TableColumn tc : tableColumns) {
			if ("id".equals(tc.getPropName()) || "createBy".equals(tc.getPropName()) || "createTime".equals(tc.getPropName())) {
				continue;
			} else if("version".equals(tc.getPropName())) {
				sb.append("\t").append("VERSION = VERSION + 1,").append("\r\n");
			} else {
				// <if test="entity.delFlag != null and entity.delFlag != ''"> DEL_FLAG = #{entity.delFlag},  </if>
				String paramName = DEF_PARAM_ALIAS + "." + tc.getPropName();
				String columnName = tc.getColName();
				if (String.class.getName().equals(tc.getPropType().getName())) {
					sb.append("\t").append(String.format("<if test=\"%s != null and %s != ''\"> %s=#{%s}, </if>", paramName, paramName, columnName, paramName)).append("\r\n");
				} else {
					sb.append("\t").append(String.format("<if test=\"%s != null\"> %s=#{%s}, </if>", paramName, columnName, paramName)).append("\r\n");
				}
			}
		}
		sb.append("</if>");
		return sb.toString();
	}
	
}

