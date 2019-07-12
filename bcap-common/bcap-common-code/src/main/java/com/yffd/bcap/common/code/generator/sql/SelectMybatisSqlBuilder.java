package com.yffd.bcap.common.code.generator.sql;

import com.yffd.bcap.common.support.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月20日 下午5:25:20 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SelectMybatisSqlBuilder extends MybatisSqlBuilder {

	public static final String SQL_ID_TABLE_COLUMNS = "table_columns";
	public static final String SQL_ID_CONDITIONS_ORDERBY = "conditions_orderby";
	public static final String SQL_ID_CONDITIONS_LIMIT = "conditions_limit";
	public static final String SQL_ID_SELECT_ONE = "selectOneBy";
	public static final String SQL_ID_SELECT_LIST = "selectListBy";
	public static final String SQL_ID_SELECT_COUNT = "selectCountBy";
	public static final String DEF_PARAM_TYPE = "java.util.Map";
	public static final String DEF_RESULT_TYPE = "java.lang.Integer";
	public static final String DEF_TABLE_ALIAS = "t";
	
	public String buildTableName(Class<?> rootEntityClazz) {
		String tableName = this.entityName2tableName(rootEntityClazz, "rootEntity");
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 表名 -->").append("\r\n");
		sb.append(String.format("<sql id=\"%s\"> %s </sql>", DEF_SQL_ID_TABLE_NAME, tableName));
		return sb.toString();
	}
	
	public String buildTableColumns(Class<?> rootEntityClazz, Class<?> baserootEntityClazz) {
		String tableColumnsId = SQL_ID_TABLE_COLUMNS;
		String tableAlias = DEF_TABLE_ALIAS;
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 列名 -->").append("\r\n");
		sb.append(String.format("<sql id=\"%s\">", tableColumnsId)).append("\r\n");
		sb.append(this.linesFmt(this.buildTableColumnContent(tableColumnsId, tableAlias, rootEntityClazz, baserootEntityClazz), "\t", null));
		sb.append("</sql>").append("\r\n");
		return sb.toString();
	}
	
	public String buildLimit() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 分页条件 -->").append("\r\n");
		sb.append("<sql id=\""+SQL_ID_CONDITIONS_LIMIT+"\"><if test=\"page != null\"> limit #{page.startIndex}, #{page.pageLimit} </if></sql>").append("\r\n");
		return sb.toString();
	}
	
	public String buildOrderBy() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 排序条件 -->").append("\r\n");
		sb.append("<sql id=\""+SQL_ID_CONDITIONS_ORDERBY+"\">").append("\r\n");
		sb.append("\t").append("<choose>").append("\r\n");
		sb.append("\t").append("\t").append("<when test=\"orderBy != null and orderBy !=''\"> ORDER BY #{orderBy} </when>").append("\r\n");
		sb.append("\t").append("\t").append("<otherwise> ORDER BY id desc </otherwise>").append("\r\n");
		sb.append("\t").append("</choose>").append("\r\n");
		sb.append("</sql>").append("\r\n");
		return sb.toString();
	}
	
	public String buildWhere(Class<?> rootEntityClazz, Class<?> baserootEntityClazz) {
		String tableAlias = DEF_TABLE_ALIAS;
		String paramAlias = DEF_PARAM_ALIAS;
		
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 动态组装复合查询条件 -->").append("\r\n");
		sb.append("<sql id=\"conditions_where\">").append("\r\n");
		
		String whereContent = this.buildWhereConditions(tableAlias, paramAlias, rootEntityClazz, baserootEntityClazz);
		sb.append(this.linesFmt(whereContent, "\t", null));
		
		sb.append("</sql>").append("\r\n");
		
		return sb.toString();
	}
	
	
	public String buildSelectListBy(Class<?> rootEntityClazz) {
		String asTableAliasName = " as " + DEF_TABLE_ALIAS + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 条件查询 -->").append("\r\n");
		sb.append("<select id=\"selectListBy\" parameterType=\""+DEF_PARAM_TYPE+"\" resultType=\""+rootEntityClazz.getName()+"\">").append("\r\n");
		sb.append("\t").append("select <include refid=\""+SQL_ID_TABLE_COLUMNS+"\" />").append("\r\n");
		sb.append("\t").append("from <include refid=\""+DEF_SQL_ID_TABLE_NAME+"\"/>").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t").append("\t").append("<include refid=\""+DEF_SQL_ID_WHERE+"\" />").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("\t").append("<include refid=\""+SQL_ID_CONDITIONS_ORDERBY+"\" />").append("\r\n");
		sb.append("\t").append("<include refid=\""+SQL_ID_CONDITIONS_LIMIT+"\" />").append("\r\n");
		sb.append("</select>").append("\r\n");
		return sb.toString();
	}
	
	public String buildSelectCountBy() {
		String asTableAliasName = " as " + DEF_TABLE_ALIAS + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 统计查询 -->").append("\r\n");
		sb.append("<select id=\"selectCountBy\" parameterType=\""+DEF_PARAM_TYPE+"\" resultType=\"java.lang.Integer\">").append("\r\n");
		sb.append("\t").append("select count(1) from ").append("\r\n");
		sb.append("\t").append("<include refid=\""+DEF_SQL_ID_TABLE_NAME+"\"/>").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t").append("\t").append("<include refid=\""+DEF_SQL_ID_WHERE+"\" />").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("</select>").append("\r\n");
		return sb.toString();
	}
	
	public String buildSelectOneBy(Class<?> rootEntityClazz) {
		String asTableAliasName = " as " + DEF_TABLE_ALIAS + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 单条查询 -->").append("\r\n");
		sb.append("<select id=\""+SQL_ID_SELECT_ONE+"\" parameterType=\""+DEF_PARAM_TYPE+"\" resultType=\""+rootEntityClazz.getName()+"\">").append("\r\n");
		sb.append("\t").append("select <include refid=\""+SQL_ID_TABLE_COLUMNS+"\" /> ").append("\r\n");
		sb.append("\t").append("from <include refid=\""+DEF_SQL_ID_TABLE_NAME+"\"/>").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t").append("\t").append("<include refid=\""+DEF_SQL_ID_WHERE+"\" />").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("</select>").append("\r\n");
		return sb.toString();
	}
	
	private String buildTableColumnContent(String tableColumnsId, String tableAlias, Class<?> rootEntityClazz, Class<?> baserootEntityClazz) {
		List<TableColumn> tableColumns = this.prop2column(rootEntityClazz, baserootEntityClazz);
		int size = tableColumns.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			TableColumn tc = tableColumns.get(i);
			if (StringUtils.isNotBlank(tableAlias)) sb.append(tableAlias).append(".");
			sb.append(tc.getColName()).append(" as ").append(tc.getPropName());
			if (i < (size - 1)) sb.append(", ");
			if (((i+1) % 5) == 0) sb.append("\r\n");
		}
		return sb.toString();
	}
	
	public static final List<String> DEF_SKIP_PROPS = new ArrayList<String>();
	
	static {
		DEF_SKIP_PROPS.add("version");
		DEF_SKIP_PROPS.add("delFlag");
		DEF_SKIP_PROPS.add("createBy");
//		DEF_SKIP_PROPS.add("createTime");
		DEF_SKIP_PROPS.add("updateBy");
		DEF_SKIP_PROPS.add("updateTime");
	}
	
	@Override
	protected String buildWhereConditions(String tableAlias, String paramAlias, Class<?> rootEntityClazz, Class<?> baserootEntityClazz) {
		List<TableColumn> equalColums = new ArrayList<>();
		List<TableColumn> likeColums = new ArrayList<>();
		List<TableColumn> inColums = new ArrayList<>();
		
		List<TableColumn> columns = this.prop2column(rootEntityClazz, baserootEntityClazz);
		for (TableColumn tc : columns) {
			String propName = tc.getPropName();
			if (DEF_SKIP_PROPS.contains(propName)) continue;
			// id 和 code 可用 in条件
			if (propName.matches(DEF_REGEX_IN_ID) || propName.matches(DEF_REGEX_IN_CODE)) {
				inColums.add(tc);
			}
			if (propName.matches(DEF_REGEX_LIKE_NAME)) {
				likeColums.add(tc);
			} else {
				equalColums.add(tc);
			}
		}
		
		String paramMapAlias = DEF_PARAM_MAP_ALIAS;
		
		List<String> equalStringList = this.buildIfEqual(equalColums, tableAlias, paramAlias);
		List<String> likeStringList = this.buildIfLike(likeColums, tableAlias, paramAlias);
		List<String> inStringList = this.buildIfIn(inColums, tableAlias, paramMapAlias);
		String conditions = this.buildConditions(paramAlias, paramMapAlias, equalStringList, likeStringList, inStringList);
		return conditions;
	}
}

