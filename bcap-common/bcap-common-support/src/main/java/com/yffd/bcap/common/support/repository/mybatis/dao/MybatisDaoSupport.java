package com.yffd.bcap.common.support.repository.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.bcap.common.domain.model.page.PageData;
import com.yffd.bcap.common.domain.model.page.PageInfo;
import com.yffd.bcap.common.support.exception.MybatisException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yffd.bcap.common.support.util.StringUtil;

/**
 * @Description  mybatis dao常用操作类.
 * @Date		 2018年4月18日 下午5:39:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MybatisDaoSupport {
	private static final Logger LOG = LoggerFactory.getLogger(MybatisDaoSupport.class);
	
    private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Integer selectCountBy(String sqlId, Object parameter) {
		if(StringUtil.isBlank(sqlId)) throw MybatisException.DB_SQL_ID_EMPTY();
		LOG.info("===sqlId[selectCountBy]:" + sqlId);
        return this.getSqlSession().selectOne(sqlId, parameter);
	}

	public <T> List<T> selectListBy(String sqlId, Object parameter) {
		if(StringUtil.isBlank(sqlId)) throw MybatisException.DB_SQL_ID_EMPTY();
		LOG.info("===sqlId[selectListBy]:" + sqlId);
		return this.getSqlSession().selectList(sqlId, parameter);
	}

	public <T> T selectOneBy(String sqlId, Object parameter) {
		if(StringUtil.isBlank(sqlId)) throw MybatisException.DB_SQL_ID_EMPTY();
		LOG.info("===sqlId[selectOneBy]:" + sqlId);
		return this.getSqlSession().selectOne(sqlId, parameter);
	}

	public Integer insertBy(String sqlId, Object parameter) {
		if(StringUtil.isBlank(sqlId)) throw MybatisException.DB_SQL_ID_EMPTY();
		LOG.info("===sqlId[insertBy]:" + sqlId);
		return this.getSqlSession().insert(sqlId, parameter);
	}

	public Integer updateBy(String sqlId, Object parameter) {
		if(StringUtil.isBlank(sqlId)) throw MybatisException.DB_SQL_ID_EMPTY();
		LOG.info("===sqlId[updateBy]:" + sqlId);
		return this.getSqlSession().update(sqlId, parameter);
	}

	public Integer deleteBy(String sqlId, Object parameter) {
		if(StringUtil.isBlank(sqlId)) throw MybatisException.DB_SQL_ID_EMPTY();
		LOG.info("===sqlId[deleteBy]:" + sqlId);
		return this.getSqlSession().delete(sqlId, parameter);
	}
	
	/*************************************************************************************/
	/*************************************************************************************/
	
	public <T> PageData<T> selectPageBy(String sqlId, String countSqlId, Map<String, Object> paramMap, PageInfo page) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
		if(null!=page) {
			if(StringUtil.isBlank(countSqlId)) throw MybatisException.DB_SQL_ID_EMPTY("countSqlId 不能为空");
			Integer totalRecord = this.getSqlSession().selectOne(countSqlId, paramMap); // 统计总记录数
			page.setTotalRecord(totalRecord);
			paramMap.put("page", page); // 根据页面传来的分页参数构造SQL分页参数
		}
		if(StringUtil.isBlank(sqlId)) throw MybatisException.DB_SQL_ID_EMPTY();
		LOG.info("===sqlId[selectPageBy]:" + sqlId);
		List<T> recordList = this.getSqlSession().selectList(sqlId, paramMap); // 获取分页数据集
		return new PageData<T>(page, recordList);
	}
	
}

