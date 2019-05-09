package com.yffd.bcap.common.code.generator.sql;

import com.yffd.bcap.common.support.util.CollectionUtil;
import com.yffd.bcap.common.support.util.JavaBeanUtil;
import com.yffd.bcap.common.support.util.StringUtil;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月18日 下午3:44:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MybatisSqlBuilder {

	public static final String DEF_TABLE_ALIAS = "t";
	public static final String DEF_PARAM_ALIAS = "entity";
	public static final String DEF_PARAM_MAP_ALIAS = "propsMap";
	public static final String DEF_REGEX_IN_ID = ".*((?i)i)d.*";
	public static final String DEF_REGEX_IN_CODE = ".*((?i)c)ode.*";
	public static final String DEF_REGEX_LIKE_NAME = ".*((?i)n)ame.*";
	
	public static final String DEF_ENTITY_SUFFIX = "Entity";
	public static final String DEF_PARAM_SUFFIX = "Iter";
	public static final String DEF_SQL_ID_TABLE_NAME = "table_name";
	public static final String DEF_SQL_ID_WHERE = "conditions_where";

	public static final List<String> DEF_SKIP_PROPS = new ArrayList<String>();
	
	static {
		DEF_SKIP_PROPS.add("version");
		DEF_SKIP_PROPS.add("delFlag");
		DEF_SKIP_PROPS.add("createBy");
		DEF_SKIP_PROPS.add("createTime");
		DEF_SKIP_PROPS.add("updateBy");
		DEF_SKIP_PROPS.add("updateTime");
	}
	
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
	
	protected String buildConditions(String paramAlias, String paramMapAlias, 
			List<String> equalStringList, List<String> likeStringList, List<String> inStringList) {
		StringBuilder sb = new StringBuilder();
		// 实体属性
		if (CollectionUtil.isNotEmpty(equalStringList) || CollectionUtil.isNotEmpty(likeStringList)) {
			sb.append("<!-- 实体属性过滤 -->").append("\r\n");
			sb.append(String.format("<if test=\"%s != null\">", paramAlias)).append("\r\n");
			// equal
			if (CollectionUtil.isNotEmpty(equalStringList)) {
				sb.append(String.format("%s<!-- Equal query -->", "\t")).append("\r\n");
				for (String line : equalStringList) {
					sb.append(String.format("%s%s", "\t", line)).append("\r\n");
				}
			}
			// like
			if (CollectionUtil.isNotEmpty(likeStringList)) {
				sb.append(String.format("%s<!-- Like query -->", "\t")).append("\r\n");
				for (String line : likeStringList) {
					sb.append(String.format("%s%s", "\t", line)).append("\r\n");
				}
			}
			
			sb.append("</if>").append("\r\n");
		}
		
		// 非实体属性 in
		if (CollectionUtil.isNotEmpty(inStringList)) {
			sb.append("<!-- 非实体属性过滤 -->").append("\r\n");
			sb.append(String.format("<if test=\"%s != null\">", paramMapAlias)).append("\r\n");
			
			sb.append(String.format("%s<!-- In query -->", "\t")).append("\r\n");
			for (String line : inStringList) {
				String tmp = String.format("%s", line);
				sb.append(this.linesFmt(tmp, "\t", "\r\n"));
			}
						
			sb.append("</if>");
		}
		
		return sb.toString();
	}
	
	/**
	 * 多条件过滤
	 * @Date	2018年9月19日 下午1:59:59 <br/>
	 * @author  zhangST
	 * @param tableColumns
	 * @param tableAlias
	 * @param paramMapAlias
	 * @return
	 */
	protected List<String> buildIfIn(List<TableColumn> tableColumns, String tableAlias, String paramMapAlias) {
		if (CollectionUtil.isEmpty(tableColumns)) return null;
		List<String> retList = new ArrayList<>();
		for (TableColumn tc : tableColumns) {
			String propName = tc.getPropName();
			String colName = tc.getColName();
			
			if (StringUtil.isBlank(paramMapAlias)) paramMapAlias = DEF_PARAM_MAP_ALIAS;
			String paramName = paramMapAlias + "." + propName + DEF_PARAM_SUFFIX;
			String columnName = colName;
			if (StringUtil.isNotBlank(tableAlias)) columnName = tableAlias + "." + columnName;
			
			StringBuilder sb = new StringBuilder();
			sb.append("<if test=\""+paramName+" != null and "+ paramName +".size()>0\">").append("\r\n");
			sb.append("and ").append(columnName).append(" in ")
			.append("<foreach item=\"item\" index=\"index\" collection=\""+paramName+"\" open=\"(\" separator=\",\" close=\") \">")
			.append("#{item}").append("</foreach>").append("\r\n");
			sb.append("</if>");
			retList.add(sb.toString());
		}
		return retList;
	}
	
	/**
	 * 多条件过滤
	 * @author  zhangST
	 * @param tableColumns
	 * @param tableAlias
	 * @param paramAlias
	 * @return
	 */
	protected List<String> buildIfEqual(List<TableColumn> tableColumns, String tableAlias, String paramAlias) {
		if (CollectionUtil.isEmpty(tableColumns)) return null;
		List<String> retList = new ArrayList<>();
		for (TableColumn tc : tableColumns) {
			Class<?> propType = tc.getPropType();
			String propName = tc.getPropName();
			String colName = tc.getColName();
			
			if (StringUtil.isBlank(paramAlias)) paramAlias = DEF_PARAM_ALIAS;
			String paramName = paramAlias + "." + propName;
			String columnName = colName;
			if (StringUtil.isNotBlank(tableAlias)) columnName = tableAlias + "." + columnName;
			
			if(String.class.getName().equals(propType.getName())) {
				// <if test="entity.delFlag != null and entity.delFlag != ''"> and DEL_FLAG = #{entity.delFlag} </if>
				String tmp = String.format("<if test=\"%s != null and %s != ''\"> and %s = #{%s} </if>", paramName, paramName, columnName, paramName);
				retList.add(tmp);
			} else if (Date.class.getName().equals(propType.getName())) {
				// <if test="entity.version != null "><![CDATA[ and VERSION = #{entity.version} ]]></if>
				String tmp = String.format("<if test=\"%s != null\"><![CDATA[ and %s = #{%s} ]]></if>", paramName, columnName, paramName);
				retList.add(tmp);
			} else {
				// <if test="entity.version != null "> and VERSION = #{entity.version} </if>
				String tmp = String.format("<if test=\"%s != null\"> and %s = #{%s} </if>", paramName, columnName, paramName);
				retList.add(tmp);
			}
		}
		return retList;
	}
	
	/**
	 * 多条件过滤
	 * @Date	2018年9月19日 下午2:30:44 <br/>
	 * @author  zhangST
	 * @param tableColumns
	 * @param tableAlias
	 * @param paramAlias	默认：props
	 * @return
	 */
	protected List<String> buildIfLike(List<TableColumn> tableColumns, String tableAlias, String paramAlias) {
		if (CollectionUtil.isEmpty(tableColumns)) return null;
		List<String> retList = new ArrayList<>();
		for (TableColumn tc : tableColumns) {
			Class<?> propType = tc.getPropType();
			String propName = tc.getPropName();
			String colName = tc.getColName();
			
			if (StringUtil.isBlank(paramAlias)) paramAlias = DEF_PARAM_ALIAS;
			String paramName = paramAlias + "." + propName;
			String columnName = colName;
			if (StringUtil.isNotBlank(tableAlias)) columnName = tableAlias + "." + columnName;
			
			if (String.class.getName().equals(propType.getName())) {
				// <if test="entity.userName != null and entity.userName != ''"> and t.USER_NAME like CONCAT('%', #{entity.userName}, '%') </if>
				String tmp = String.format("<if test=\"%s != null and %s != ''\"> and %s like CONCAT('%%', #{%s}, '%%') </if>",
						paramName, paramName, columnName, paramName);
				retList.add(tmp);
			} else {
				// <if test="entity.userName != null"> and t.USER_NAME like CONCAT('%', #{entity.userName}, '%') </if>
				String tmp = String.format("<if test=\"%s != null\"> and %s like CONCAT('%s', #{%s}, '%s') </if>",
						paramName, columnName, "%s", paramName, "%s");
				retList.add(tmp);
			}
		}
		return retList;
	}
	
	/**
	 * 单条件过滤
	 * @param paramAlias
	 * @param paramMapAlias
	 * @param equalStringList
	 * @param likeStringList
	 * @param inStringList
	 * @return
	 */
	protected String buildWhenConditions(String paramAlias, String paramMapAlias, 
			List<String> equalStringList, List<String> likeStringList, List<String> inStringList) {
		StringBuilder sb = new StringBuilder();
		// 实体属性
		if (CollectionUtil.isNotEmpty(equalStringList) || CollectionUtil.isNotEmpty(likeStringList)) {
			// equal
			if (CollectionUtil.isNotEmpty(equalStringList)) {
				sb.append(String.format("%s<!-- 实体属性过滤(Equal query) -->", "\t")).append("\r\n");
				for (String line : equalStringList) {
					sb.append(String.format("%s%s", "\t", line)).append("\r\n");
				}
			}
			// like
			if (CollectionUtil.isNotEmpty(likeStringList)) {
				sb.append(String.format("%s<!-- 实体属性过滤(Like query) -->", "\t")).append("\r\n");
				for (String line : likeStringList) {
					sb.append(String.format("%s%s", "\t", line)).append("\r\n");
				}
			}
		}
		
		// 非实体属性 in
		if (CollectionUtil.isNotEmpty(inStringList)) {
			sb.append(String.format("%s<!-- 非实体属性过滤(In query) -->", "\t")).append("\r\n");
			for (String line : inStringList) {
				String tmp = String.format("%s", line);
				sb.append(this.linesFmt(tmp, "\t", "\r\n"));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 单条件过滤
	 * @Date	2018年9月19日 下午1:59:59 <br/>
	 * @author  zhangST
	 * @param tableColumns
	 * @param tableAlias
	 * @param paramMapAlias
	 * @return
	 */
	protected List<String> buildWhenIn(List<TableColumn> tableColumns, String tableAlias, String paramMapAlias) {
		if (CollectionUtil.isEmpty(tableColumns)) return null;
		List<String> retList = new ArrayList<>();
		for (TableColumn tc : tableColumns) {
			String propName = tc.getPropName();
			String colName = tc.getColName();
			
			if (StringUtil.isBlank(paramMapAlias)) paramMapAlias = DEF_PARAM_MAP_ALIAS;
			String paramName = paramMapAlias + "." + propName + DEF_PARAM_SUFFIX;
			String columnName = colName;
			if (StringUtil.isNotBlank(tableAlias)) columnName = tableAlias + "." + columnName;
//			<when test="propsMap != null and propsMap.idIter != null and propsMap.idIter.size()>0">
//			and ID in <foreach item="item" index="index" collection="propsMap.idIter" open="(" separator="," close=") ">#{item}</foreach>
//			</when>
			StringBuilder sb = new StringBuilder();
			sb.append("<when test=\""+paramMapAlias+" != null and "+paramName+" != null and "+ paramName +".size()>0\">").append("\r\n");
			sb.append("and ").append(columnName).append(" in ")
			.append("<foreach item=\"item\" index=\"index\" collection=\""+paramName+"\" open=\"(\" separator=\",\" close=\") \">")
			.append("#{item}").append("</foreach>").append("\r\n");
			sb.append("</when>");
			retList.add(sb.toString());
		}
		return retList;
	}
	
	/**
	 * 单条件过滤
	 * @Date	2018年9月19日 下午2:00:58 <br/>
	 * @author  zhangST
	 * @param tableColumns
	 * @param tableAlias
	 * @param paramAlias
	 * @return
	 */
	protected List<String> buildWhenEqual(List<TableColumn> tableColumns, String tableAlias, String paramAlias) {
		if (CollectionUtil.isEmpty(tableColumns)) return null;
		List<String> retList = new ArrayList<>();
		for (TableColumn tc : tableColumns) {
			Class<?> propType = tc.getPropType();
			String propName = tc.getPropName();
			String colName = tc.getColName();
			
			if (StringUtil.isBlank(paramAlias)) paramAlias = DEF_PARAM_ALIAS;
			String paramName = paramAlias + "." + propName;
			String columnName = colName;
			if (StringUtil.isNotBlank(tableAlias)) columnName = tableAlias + "." + columnName;
			
			if(String.class.getName().equals(propType.getName())) {
				// <when test="entity != null and entity.id != null and entity.id != ''"> and ID = #{entity.id} </when>
				String tmp = String.format("<when test=\"%s != null and %s != null and %s != ''\"> and %s = #{%s} </when>", 
						paramAlias, paramName, paramName, columnName, paramName);
				retList.add(tmp);
			} else if (Date.class.getName().equals(propType.getName())) {
				// <when test="entity != null and entity.createTime != null "><![CDATA[ and CREATE_TIME = #{entity.createTime} ]]></when>
				String tmp = String.format("<when test=\"%s != null and %s != null\"><![CDATA[ and %s = #{%s} ]]></when>", 
						paramAlias, paramName, columnName, paramName);
				retList.add(tmp);
			} else {
				// <when test="entity != null and entity.version != null "> and VERSION = #{entity.version} </if>
				String tmp = String.format("<when test=\"%s != null and %s != null\"> and %s = #{%s} </when>", 
						paramAlias, paramName, columnName, paramName);
				retList.add(tmp);
			}
		}
		return retList;
	}
	
	/**
	 * 单条件过滤
	 * @Date	2018年9月19日 下午2:30:44 <br/>
	 * @author  zhangST
	 * @param tableColumns
	 * @param tableAlias
	 * @param paramAlias	默认：props
	 * @return
	 */
	protected List<String> buildWhenLike(List<TableColumn> tableColumns, String tableAlias, String paramAlias) {
		if (CollectionUtil.isEmpty(tableColumns)) return null;
		List<String> retList = new ArrayList<>();
		for (TableColumn tc : tableColumns) {
			Class<?> propType = tc.getPropType();
			String propName = tc.getPropName();
			String colName = tc.getColName();
			
			if (StringUtil.isBlank(paramAlias)) paramAlias = DEF_PARAM_ALIAS;
			String paramName = paramAlias + "." + propName;
			String columnName = colName;
			if (StringUtil.isNotBlank(tableAlias)) columnName = tableAlias + "." + columnName;
			
			if (String.class.getName().equals(propType.getName())) {
				// <when test="entity != null and entity.userName != null and entity.userName != ''"> and t.USER_NAME like CONCAT('%', #{entity.userName}, '%') </when>
				String tmp = String.format("<when test=\"%s != null and %s != null and %s != ''\"> and %s like CONCAT('%%', #{%s}, '%%') </when>",
						paramAlias, paramName, paramName, columnName, paramName);
				retList.add(tmp);
			} else {
				// <when test="entity != null and entity.userName != null"> and t.USER_NAME like CONCAT('%', #{entity.userName}, '%') </when>
				String tmp = String.format("<when test=\"%s != null and %s != null\"> and %s like CONCAT('%s', #{%s}, '%s') </when>",
						paramAlias, paramName, columnName, "%s", paramName, "%s");
				retList.add(tmp);
			}
		}
		return retList;
	}
	
	/*********************************************************************************/
	/*********************************************************************************/
	
	public String entityName2tableName(Class<?> entityClazz, String entitySuffix) {
		return this.entityName2tableName(entityClazz, null, entitySuffix, null, null);
	}
	
	public List<TableColumn> prop2column(Class<?> entityClazz, Class<?> baseEntityClazz) {
		List<TableColumn> retList = new ArrayList<>();
		if (null == entityClazz) return retList;
		Class<?> stopClazz = Object.class;	// 排查实体类的class属性
		if (null == baseEntityClazz || Object.class.getName().equals(baseEntityClazz.getName())) stopClazz = Object.class;
		// 子类
		if (null != baseEntityClazz) stopClazz = baseEntityClazz;
		Map<String, Class<?>> props = JavaBeanUtil.getProps(entityClazz, stopClazz);
		if (CollectionUtil.isNotEmpty(props)) {
			List<TableColumn> tmpList = new ArrayList<>();
			Set<String> keys = props.keySet();
			for (String key : keys) {
				String propName = key;
				Class<?> propType = props.get(key);
				String colName = StringUtil.camel2underline(propName, true);	// 驼峰》下划线
				TableColumn tc = new TableColumn(colName, propName, propType);
				tmpList.add(tc);
			}
			
			Collections.sort(tmpList);
			retList.addAll(tmpList);
		}
		// 父类
		if (null != baseEntityClazz) {
			Map<String, Class<?>> propsBase = JavaBeanUtil.getProps(baseEntityClazz, Object.class);
			if (CollectionUtil.isNotEmpty(propsBase)) {
				List<TableColumn> tmpList = new ArrayList<>();
				Set<String> keys = propsBase.keySet();
				for (String key : keys) {
					String propName = key;
					Class<?> propType = propsBase.get(key);
					String colName = StringUtil.camel2underline(propName, true, null, null);	// 驼峰》下划线
					TableColumn tc = new TableColumn(colName, propName, propType);
					tmpList.add(tc);
				}
				
				Collections.sort(tmpList);
				retList.addAll(tmpList);
			}
		}
		
		// 处理ID
		List<TableColumn> tmp = new ArrayList<>();
		Iterator<TableColumn> it = retList.iterator();
		while (it.hasNext()) {
			TableColumn tc = it.next();
			if ("id".equalsIgnoreCase(tc.getPropName())) {
				tmp.add(tc);
				it.remove();
			}
		}
		tmp.addAll(retList);
				
		return tmp;
	}
	
	public String entityName2tableName(Class<?> entityClazz, String entityPrefix, String entitySuffix, 
			String tablePrefix, String tableSuffix) {
		String simpleName = entityClazz.getSimpleName();
		int beginIndex = StringUtil.isBlank(entityPrefix) ? 0 : simpleName.indexOf(entityPrefix) + entityPrefix.length();
		int endIndex = StringUtil.isBlank(entitySuffix) ? simpleName.length() : simpleName.lastIndexOf(entitySuffix);
		String tableName = simpleName.substring(beginIndex, endIndex);
		tableName = StringUtil.isBlank(tablePrefix) ? tableName : tablePrefix + tableName;
		tableName = StringUtil.isBlank(tableSuffix) ? tableName : tableName + tableSuffix;
		tableName = StringUtil.camel2underline(tableName, false);
		return tableName;
	}
	
	public String linesFmt(String text, String prefix, String suffix) {
		if(null==text || "".equals(text)) return null; 
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes("utf8"))));
			String line;
			while((line = reader.readLine()) !=null) {
				sb.append(prefix).append(line).append("\r\n");
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static class TableColumn implements Comparable<TableColumn> {
		private String colName;
		private String propName;
		private Class<?> propType;
		
		public TableColumn() {
			
		}

		public TableColumn(String colName, String propName, Class<?> propType) {
			super();
			this.colName = colName;
			this.propName = propName;
			this.propType = propType;
		}

		public String getColName() {
			return colName;
		}

		public void setColName(String colName) {
			this.colName = colName;
		}

		public String getPropName() {
			return propName;
		}

		public void setPropName(String propName) {
			this.propName = propName;
		}

		public Class<?> getPropType() {
			return propType;
		}

		public void setPropType(Class<?> propType) {
			this.propType = propType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((propName == null) ? 0 : propName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TableColumn other = (TableColumn) obj;
			if (propName == null) {
				if (other.propName != null)
					return false;
			} else if (!propName.equals(other.propName))
				return false;
			return true;
		}

		@Override
		public int compareTo(TableColumn o) {
			return this.propName.compareTo(o.getPropName());
		}
		
	}
}

