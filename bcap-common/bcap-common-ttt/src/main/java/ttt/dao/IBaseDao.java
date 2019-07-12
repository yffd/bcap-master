package com.yffd.bcap.common.support.ttt.dao;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;

import java.util.List;

/**
 * @Description  持久化常用操作接口.
 * @Date		 2018年4月18日 下午5:38:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface IBaseDao<E> {

	/**
	 * 查询：统计
	 * @Date	2018年4月20日 下午2:55:01 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @return
	 */
	Integer selectCount(E rootEntity);
	
	/**
	 * 查询：单条
	 * @Date	2018年4月20日 下午2:55:16 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @return
	 */
	E selectOne(E rootEntity);
	
	/**
	 * 查询：列表
	 * @Date	2018年4月20日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param orderBy
	 * @return
	 */
	List<E> selectListWithOrder(E rootEntity, String orderBy);
	
	/**
	 * 查询：分页
	 * @Date	2018年4月20日 下午2:55:40 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param orderBy
	 * @param page
	 * @return
	 */
	PageData<E> selectPageWithOrder(E rootEntity, String orderBy, PageInfo page);
	
	/**
	 * 查询：存在并且唯一校验
	 * @Date	2018年4月19日 上午11:12:57 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @return
	 */
	Boolean exsistAndUnique(E rootEntity);

	/**
	 * 查询：存在校验
	 * @Date	2018年4月20日 下午2:55:52 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @return
	 */
	Boolean exsist(E rootEntity);
	
	/**
	 * 添加：单条
	 * @Date	2018年4月19日 上午10:32:50 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @return
	 */
	Integer insertOne(E rootEntity);
	
	/**
	 * 添加：批量
	 * @Date	2018年4月19日 上午10:33:10 <br/>
	 * @author  zhangST
	 * @param rootEntityList
	 * @return
	 */
	Integer insertBatch(List<E> rootEntityList);
	
	/**
	 * 修改
	 * @Date	2018年4月19日 上午10:33:20 <br/>
	 * @author  zhangST
	 * @param rootEntity			待修改“属性名-值”集合
	 * @param rootEntityOld			条件-旧对象“属性名-值”集合
	 * @return
	 */
	Integer update(E rootEntity, E rootEntityOld);
	
	/**
	 * 删除
	 * @Date	2018年4月19日 上午10:35:20 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @return
	 */
	Integer delete(E rootEntity);
	
}

