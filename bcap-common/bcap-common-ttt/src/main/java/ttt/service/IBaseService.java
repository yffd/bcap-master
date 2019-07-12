package com.yffd.bcap.common.support.ttt.service;

import com.yffd.bcap.common.model.login.LoginInfo;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;

import java.util.List;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月24日 下午6:22:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface IBaseService<E> {
	
	/**
	 * 查询：统计
	 * @Date	2018年4月20日 下午2:55:01 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param loginInfo
	 * @return
	 */
	Integer findCount(E rootEntity, LoginInfo loginInfo);
	
	/**
	 * 查询：单条
	 * @Date	2018年4月20日 下午2:55:16 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param loginInfo
	 * @return
	 */
	E findOne(E rootEntity, LoginInfo loginInfo);
	
	/**
	 * 查询：列表
	 * @Date	2018年4月20日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param orderBy
	 * @param loginInfo
	 * @return
	 */
	List<E> findListWithOrder(E rootEntity, String orderBy, LoginInfo loginInfo);
	
	/**
	 * 查询：列表
	 * @Date	2018年4月25日 下午1:44:01 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param loginInfo
	 * @return
	 */
	List<E> findList(E rootEntity, LoginInfo loginInfo);
	
	/**
	 * 查询：全部
	 * @Date	2018年4月25日 下午1:49:38 <br/>
	 * @author  zhangST
	 * @return
	 */
	List<E> findAll();
	
	/**
	 * 查询：全部
	 * @Date	2018年4月25日 下午1:49:43 <br/>
	 * @author  zhangST
	 * @param orderBy
	 * @return
	 */
	List<E> findAllWithOrder(String orderBy);
	
	/**
	 * 查询：分页
	 * @Date	2018年4月20日 下午2:55:40 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param orderBy
	 * @param page
	 * @param loginInfo
	 * @return
	 */
	PageData<E> findPageWithOrder(E rootEntity, String orderBy, PageInfo page, LoginInfo loginInfo);
	
	/**
	 * 查询：分页
	 * @Date	2018年4月25日 下午1:46:27 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param page
	 * @param loginInfo
	 * @return
	 */
	PageData<E> findPage(E rootEntity, PageInfo page, LoginInfo loginInfo);
	
	/**
	 * 查询：存在并且唯一校验
	 * @Date	2018年4月19日 上午11:12:57 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param loginInfo
	 * @return
	 */
	Boolean exsistAndUnique(E rootEntity, LoginInfo loginInfo);

	/**
	 * 查询：存在校验
	 * @Date	2018年4月20日 下午2:55:52 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param loginInfo
	 * @return
	 */
	Boolean exsist(E rootEntity, LoginInfo loginInfo);
	
	/**
	 * 添加：单条
	 * @Date	2018年4月19日 上午10:32:50 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param loginInfo
	 * @return
	 */
	Integer addOne(E rootEntity, LoginInfo loginInfo);
	
	/**
	 * 添加：批量
	 * @Date	2018年4月19日 上午10:33:10 <br/>
	 * @author  zhangST
	 * @param rootEntityList
	 * @param loginInfo
	 * @return
	 */
	Integer addList(List<E> rootEntityList, LoginInfo loginInfo);
	
	/**
	 * 修改
	 * @Date	2018年4月19日 上午10:33:20 <br/>
	 * @author  zhangST
	 * @param rootEntity				待修改“属性名-值”集合
	 * @param rootEntityOld				条件-旧对象“属性名-值”集合
	 * @param loginInfo
	 * @return
	 */
	Integer update(E rootEntity, E rootEntityOld, LoginInfo loginInfo);
	
	/**
	 * 删除
	 * @Date	2018年4月19日 上午10:35:20 <br/>
	 * @author  zhangST
	 * @param rootEntity
	 * @param loginInfo
	 * @return
	 */
	Integer delete(E rootEntity, LoginInfo loginInfo);

}

