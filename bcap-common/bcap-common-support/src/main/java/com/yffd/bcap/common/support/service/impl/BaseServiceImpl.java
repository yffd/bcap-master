package com.yffd.bcap.common.support.service.impl;

import com.yffd.bcap.common.domain.model.login.LoginInfo;
import com.yffd.bcap.common.domain.model.page.PageData;
import com.yffd.bcap.common.domain.model.page.PageInfo;
import com.yffd.bcap.common.support.dao.IBaseDao;
import com.yffd.bcap.common.support.service.IBaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  业务逻辑操作，需要注入自己实现的Dao类.
 * @Date		 2018年5月8日 下午5:04:19 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class BaseServiceImpl<E> implements IBaseService<E> {

	protected abstract IBaseDao<E> getBindDao();
	
	public abstract void beforeSetPropertiesForQuery(E entity, LoginInfo loginInfo);
	public abstract void beforeSetPropertiesForAdd(E entity, LoginInfo loginInfo);
	public abstract void beforeSetPropertiesForUpdate(E entity, LoginInfo loginInfo);
	public abstract void beforeSetPropertiesForDelete(E entity, LoginInfo loginInfo);
	
	@Override
	public Integer findCount(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().selectCount(entity);
	}

	@Override
	public E findOne(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().selectOne(entity);
	}

	@Override
	public List<E> findListWithOrder(E entity, String orderBy, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().selectListWithOrder(entity, orderBy);
	}

	@Override
	public List<E> findList(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().selectListWithOrder(entity, null);
	}

	@Override
	public List<E> findAll() {
		return this.getBindDao().selectListWithOrder(null, null);
	}

	@Override
	public List<E> findAllWithOrder(String orderBy) {
		return this.getBindDao().selectListWithOrder(null, orderBy);
	}

	@Override
	public PageData<E> findPageWithOrder(E entity, String orderBy, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().selectPageWithOrder(entity, orderBy, page);
	}

	@Override
	public PageData<E> findPage(E entity, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().selectPageWithOrder(entity, null, page);
	}
	
	@Override
	public Boolean exsistAndUnique(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().exsistAndUnique(entity);
	}

	@Override
	public Boolean exsist(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return this.getBindDao().exsist(entity);
	}

	@Override
	public Integer save(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForAdd(entity, loginInfo);
		return this.getBindDao().insertOne(entity);
	}

	@Override
	public Integer save(List<E> entityList, LoginInfo loginInfo) {
		if(null==entityList || entityList.size()==0) return 0;
		List<E> tmpList = new ArrayList<E>();
		for(E entity : entityList) {
			this.beforeSetPropertiesForAdd(entity, loginInfo);
			tmpList.add(entity);
		}
		return this.getBindDao().insertBatch(tmpList);
	}

	@Override
	public Integer update(E entity, E entityOld, LoginInfo loginInfo) {
		this.beforeSetPropertiesForUpdate(entity, loginInfo);
		return this.getBindDao().update(entity, entityOld);
	}

	@Override
	public Integer delete(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForDelete(entity, loginInfo);
		return this.getBindDao().delete(entity);
	}
	
}

