package com.yffd.bcap.common.support.ttt.service.impl;

import com.yffd.bcap.common.model.login.LoginInfo;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.support.ttt.dao.IBaseDao;
import com.yffd.bcap.common.support.ttt.service.IBaseService;

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
	
	public abstract void beforeSetPropertiesForQuery(E rootEntity, LoginInfo loginInfo);
	public abstract void beforeSetPropertiesForAdd(E rootEntity, LoginInfo loginInfo);
	public abstract void beforeSetPropertiesForUpdate(E rootEntity, LoginInfo loginInfo);
	public abstract void beforeSetPropertiesForDelete(E rootEntity, LoginInfo loginInfo);
	
	@Override
	public Integer findCount(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().selectCount(rootEntity);
	}

	@Override
	public E findOne(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().selectOne(rootEntity);
	}

	@Override
	public List<E> findListWithOrder(E rootEntity, String orderBy, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().selectListWithOrder(rootEntity, orderBy);
	}

	@Override
	public List<E> findList(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().selectListWithOrder(rootEntity, null);
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
	public PageData<E> findPageWithOrder(E rootEntity, String orderBy, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().selectPageWithOrder(rootEntity, orderBy, page);
	}

	@Override
	public PageData<E> findPage(E rootEntity, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().selectPageWithOrder(rootEntity, null, page);
	}
	
	@Override
	public Boolean exsistAndUnique(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().exsistAndUnique(rootEntity);
	}

	@Override
	public Boolean exsist(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return this.getBindDao().exsist(rootEntity);
	}

	@Override
	public Integer addOne(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForAdd(rootEntity, loginInfo);
		return this.getBindDao().insertOne(rootEntity);
	}

	@Override
	public Integer addList(List<E> rootEntityList, LoginInfo loginInfo) {
		if(null==rootEntityList || rootEntityList.size()==0) return 0;
		List<E> tmpList = new ArrayList<E>();
		for(E rootEntity : rootEntityList) {
			this.beforeSetPropertiesForAdd(rootEntity, loginInfo);
			tmpList.add(rootEntity);
		}
		return this.getBindDao().insertBatch(tmpList);
	}

	@Override
	public Integer update(E rootEntity, E rootEntityOld, LoginInfo loginInfo) {
		this.beforeSetPropertiesForUpdate(rootEntity, loginInfo);
		return this.getBindDao().update(rootEntity, rootEntityOld);
	}

	@Override
	public Integer delete(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForDelete(rootEntity, loginInfo);
		return this.getBindDao().delete(rootEntity);
	}
	
}

