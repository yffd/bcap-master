package com.yffd.bcap.common.support.ttt.dao.impl;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.support.ttt.dao.IBaseDao;
import com.yffd.bcap.common.support.ttt.persist.mybatis.dao.DefaultMybatisDao;

import java.util.List;

/**
 * @Description  持久化常用操作接口，以实体类（rootEntity）全名为sql mapper 的 命名空间.
 * @Date		 2018年4月28日 下午2:57:05 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BaseDaoImpl<E> extends DefaultMybatisDao<E> implements IBaseDao<E> {
	
	@Override
	public Integer selectCount(E rootEntity) {
		return super.selectCountBy(rootEntity, null);
	}
	
	@Override
	public E selectOne(E rootEntity) {
		return super.selectOneBy(rootEntity, null);
	}

	@Override
	public List<E> selectListWithOrder(E rootEntity, String orderBy) {
		return super.selectListBy(rootEntity, null, orderBy);
	}

	@Override
	public PageData<E> selectPageWithOrder(E rootEntity, String orderBy, PageInfo page) {
		return super.selectPageBy(rootEntity, null, orderBy, page);
	}

	@Override
	public Boolean exsistAndUnique(E rootEntity) {
		return super.exsistAndUniqueBy(rootEntity, null);
	}

	@Override
	public Boolean exsist(E rootEntity) {
		return super.exsistBy(rootEntity, null);
	}
	
	@Override
	public Integer insertOne(E rootEntity) {
		return super.insertOneBy(rootEntity);
	}

	@Override
	public Integer insertBatch(List<E> rootEntityList) {
		return super.insertBatchBy(rootEntityList);
	}

	@Override
	public Integer update(E rootEntity, E rootEntityOld) {
		return super.updateBy(rootEntity, rootEntityOld, null);
	}
	
	@Override
	public Integer delete(E rootEntity) {
		return super.deleteBy(rootEntity, null);
	}

}

