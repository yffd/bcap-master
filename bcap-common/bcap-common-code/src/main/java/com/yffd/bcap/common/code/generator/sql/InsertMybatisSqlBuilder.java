package com.yffd.bcap.common.code.generator.sql;

import com.yffd.bcap.common.support.util.StringUtil;

import java.util.List;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月19日 下午5:17:23 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class InsertMybatisSqlBuilder extends MybatisSqlBuilder {

	public static final String SQL_ID_INSERT_ONE_BY = "insertOneBy";
	public static final String SQL_ID_INSERT_BATCH_BY = "insertBatchBy";
	public static final String DEF_PARAM_TYPE = "java.util.Map";
	
	public String buildSqlInsertOne(Class<?> entityClazz, Class<?> baseEntityClazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 单条插入 -->").append("\r\n");
		sb.append(String.format("<insert id=\"%s\" parameterType=\"%s\" keyProperty=\"id\" useGeneratedKeys=\"true\">", 
				SQL_ID_INSERT_ONE_BY, DEF_PARAM_TYPE)).append("\r\n");
		
		sb.append("\t").append(String.format("insert into <include refid=\"%s\" />", DEF_SQL_ID_TABLE_NAME)).append("\r\n");
		sb.append(this.linesFmt(this.buildInto(entityClazz, baseEntityClazz), "\t", "\r\n"));
		sb.append("\t").append("values").append("\r\n");
		sb.append(this.linesFmt(buildValues(null, entityClazz, baseEntityClazz), "\t", "\r\n"));
		
		sb.append("</insert>").append("\r\n");
		return sb.toString();
	}
	
	public String buildSqlInsertBatch(Class<?> entityClazz, Class<?> baseEntityClazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 批量插入 -->").append("\r\n");
		sb.append(String.format("<insert id=\"%s\" parameterType=\"%s\" keyProperty=\"id\" useGeneratedKeys=\"true\">", 
				SQL_ID_INSERT_BATCH_BY, DEF_PARAM_TYPE)).append("\r\n");
		
		sb.append("\t").append(String.format("insert into <include refid=\"%s\" />", DEF_SQL_ID_TABLE_NAME)).append("\r\n");
		sb.append(this.linesFmt(this.buildInto(entityClazz, baseEntityClazz), "\t", "\r\n"));
		sb.append("\t").append("values").append("\r\n");
		sb.append("\t").append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">").append("\r\n");
		sb.append(this.linesFmt(buildValues("item", entityClazz, baseEntityClazz), "\t", "\r\n"));
		sb.append("\t").append("</foreach>").append("\r\n");
		
		sb.append("</insert>").append("\r\n");
		return sb.toString();
	}
	
	private String buildInto(Class<?> entityClazz, Class<?> baseEntityClazz) {
		List<TableColumn> tableColumns = this.prop2column(entityClazz, baseEntityClazz);
		int size = tableColumns.size();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < size; i++) {
			TableColumn tc = tableColumns.get(i);
			if ("id".equalsIgnoreCase(tc.getColName())) continue;
			sb.append(tc.getColName());
			if (i < (size - 1)) sb.append(", ");
			if (((i+1) % 5) == 0) sb.append("\r\n");
		}
		sb.append(")");
		return sb.toString();
//		for (TableColumn tc : tableColumns) {
//			if ("id".equalsIgnoreCase(tc.getColName())) continue;
//			sb.append(tc.getColName()).append(", ");
//		}
//		return sb.subSequence(0, sb.length() - 2).toString();
	}
	
	private String buildValues(String paramPreffix, Class<?> entityClazz, Class<?> baseEntityClazz) {
		List<TableColumn> tableColumns = this.prop2column(entityClazz, baseEntityClazz);
		int size = tableColumns.size();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < size; i++) {
			TableColumn tc = tableColumns.get(i);
			if ("id".equalsIgnoreCase(tc.getColName())) continue;
			String propName = tc.getPropName();
			String columnValue = StringUtil.isBlank(paramPreffix) ? propName : paramPreffix + "." + propName;
			sb.append(String.format("#{%s}", columnValue));
			if (i < (size - 1)) sb.append(", ");
			if (((i+1) % 5) == 0) sb.append("\r\n");
		}
		sb.append(")");
		return sb.toString();
//		for (TableColumn tc : tableColumns) {
////			 #{version}, #{delFlag}, #{createBy}
////			 #{item.version},#{item.delFlag}
//			if ("id".equalsIgnoreCase(tc.getColName())) continue;
//			String propName = tc.getPropName();
//			String columnValue = EasyStringUtils.isBlank(paramPreffix) ? propName : paramPreffix + "." + propName;
//			sb.append(String.format("#{%s}", columnValue)).append(", ");
//		}
//		return sb.subSequence(0, sb.length() - 2).toString();
	}
}

