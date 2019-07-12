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
	public Integer findCount(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return super.selectCountBy(rootEntity, null);
	}

	@Override
	public E findOne(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return (E) super.selectOneBy(rootEntity, null);
	}

	@Override
	public List<E> findListWithOrder(E rootEntity, String orderBy, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return (List<E>) super.selectListBy(rootEntity, null, orderBy);
	}

	@Override
	public List<E> findList(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return (List<E>) super.selectListBy(rootEntity, null, null);
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
	public PageData<E> findPageWithOrder(E rootEntity, String orderBy, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return (PageData<E>) super.selectPageBy(rootEntity, null, orderBy, page);
	}

	@Override
	public PageData<E> findPage(E rootEntity, PageInfo page, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return (PageData<E>) super.selectPageBy(rootEntity, null, null, page);
	}
	
	@Override
	public Boolean exsistAndUnique(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return super.exsistAndUniqueBy(rootEntity, null);
	}

	@Override
	public Boolean exsist(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForQuery(rootEntity, loginInfo);
		return super.exsistBy(rootEntity, null);
	}
	
	@Override
	public Integer addOne(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForAdd(rootEntity, loginInfo);
		return super.insertOneBy(rootEntity);
	}

	@Override
	public Integer addList(List<E> rootEntityList, LoginInfo loginInfo) {
		if(null==rootEntityList || rootEntityList.size()==0) return 0;
		List<E> tmpList = new ArrayList<>();
		for(E rootEntity : rootEntityList) {
			this.beforeSetPropertiesForAdd(rootEntity, loginInfo);
			tmpList.add(rootEntity);
		}
		return super.insertBatchBy(tmpList);
	}

	@Override
	public Integer update(E rootEntity, E rootEntityOld, LoginInfo loginInfo) {
		this.beforeSetPropertiesForUpdate(rootEntity, loginInfo);
		return super.updateBy(rootEntity, rootEntityOld, null);
	}

	@Override
	public Integer delete(E rootEntity, LoginInfo loginInfo) {
		this.beforeSetPropertiesForDelete(rootEntity, loginInfo);
		return super.deleteBy(rootEntity, null);
	}

}

