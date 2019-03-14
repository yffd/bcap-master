package com.yffd.bcap.common.core.db.dialect;

/**
 * 数据库方言接口
 */
public interface IDialect {
	
	/**
	 * 生成具有分页功能的SQL语句
	 * @param querySqlString	原sql语句
	 * @param offset			分页开始位置
	 * @param limit				每页限制
	 * @return
	 */
    public abstract String getLimitString(String querySqlString, long offset, long limit);
    
	/**
	 * 生成具有count功能的SQL语句
	 * @param querySqlString	原sql语句
	 * @return
	 */
	public abstract String getCountString(String querySqlString);

	/**
	 * 数据库种类枚举
	 */
	public static enum Type {
        MYSQL, ORACLE
    }
}

