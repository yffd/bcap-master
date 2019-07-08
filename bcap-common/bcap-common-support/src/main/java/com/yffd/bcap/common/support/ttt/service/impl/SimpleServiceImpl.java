package com.yffd.bcap.common.support.ttt.service.impl;

import com.yffd.bcap.common.model.login.LoginInfo;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.support.ttt.persist.mybatis.dao.DefaultMybatisDao;
import com.yffd.bcap.common.support.ttt.service.IBaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  业务逻辑操作，直接利用DefaultMybatisDao完成持久化操作.
 * @Date		 2018年5月8日 下午5:04:19 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class SimpleServiceImpl<E> extends DefaultMybatisDao<E> implements IBaseService<E> {

	protected abstract void beforeSetPropertiesForQuery(Object pojo, LoginInfo loginInfo);
	protected abstract void beforeSetPropertiesForAdd(Object pojo, LoginInfo loginInfo);
	protected abstract void beforeSetPropertiesForUpdate(Object pojo, LoginInfo loginInfo);
	protected abstract void beforeSetPropertiesForDelete(Object pojo, LoginInfo loginInfo);
	
	@Override
	public Integer findCount(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return super.selectCountBy(entity, null);
	}

	@Override
	public E findOne(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return (E) super.selectOneBy(entity, null);
	}

	@Override
	public List<E> findListWithOrder(E entity, String orderBy, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return (List<E>) super.selectListBy(entity, null, orderBy);
	}

	@Override
	public List<E> findList(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return (List<E>) super.selectListBy(entity, null, null);
	}

	@Override
	public List<E> findAll() {
		return (List<E>) super.selectListBy(null, null, null);
	}

	@Override
	public List<E> findAllWithOrder(String orderBy) {
		return (List<E>) super.selectListBy(null, null, orderBy);
	}

	@Override
	public PageData<E> findPageWithOrder(E entity, String orderBy, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return (PageData<E>) super.selectPageBy(entity, null, orderBy, page);
	}

	@Override
	public PageData<E> findPage(E entity, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return (PageData<E>) super.selectPageBy(entity, null, null, page);
	}
	
	@Override
	public Boolean exsistAndUnique(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return super.exsistAndUniqueBy(entity, null);
	}

	@Override
	public Boolean exsist(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(entity, loginInfo);
		return super.exsistBy(entity, null);
	}
	
	@Override
	public Integer addOne(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForAdd(entity, loginInfo);
		return super.insertOneBy(entity);
	}

	@Override
	public Integer addList(List<E> entityList, LoginInfo loginInfo) {
		if(null==entityList || entityList.size()==0) return 0;
		List<E> tmpList = new ArrayList<>();
		for(E entity : entityList) {
			this.beforeSetPropertiesForAdd(entity, loginInfo);
			tmpList.add(entity);
		}
		return super.insertBatchBy(tmpList);
	}

	@Override
	public Integer update(E entity, E entityOld, LoginInfo loginInfo) {
		this.beforeSetPropertiesForUpdate(entity, loginInfo);
		return super.updateBy(entity, entityOld, null);
	}

	@Override
	public Integer delete(E entity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForDelete(entity, loginInfo);
		return super.deleteBy(entity, null);
	}

}

