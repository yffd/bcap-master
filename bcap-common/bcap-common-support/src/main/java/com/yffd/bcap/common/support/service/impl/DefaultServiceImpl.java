package com.yffd.bcap.common.support.service.impl;

import com.yffd.bcap.common.domain.model.EntityObject;
import com.yffd.bcap.common.domain.model.login.LoginInfo;
import com.yffd.bcap.common.support.repository.mybatis.MybatisConstants;
import com.yffd.bcap.common.support.service.IBaseService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年6月6日 下午5:31:47 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DefaultServiceImpl<E> extends SimpleServiceImpl<E> implements IBaseService<E> {

	@Override
	protected void beforeSetPropertiesForQuery(Object domain, LoginInfo loginInfo) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void beforeSetPropertiesForAdd(Object domain, LoginInfo loginInfo) {
		if(domain instanceof EntityObject) {
			EntityObject entity = (EntityObject) domain;
			entity.setVersion(0);
			entity.setDelFlag("0");
			entity.setCreateTime(new Date());
			if(null!=loginInfo) entity.setCreateBy(loginInfo.getUserId());
		} else if(domain instanceof Map) {
			Map<String, Object> parameter = (Map<String, Object>) domain;
			parameter.put("version", 0);
			parameter.put("delFlag", "0");
			parameter.put("createTime", new Date());
			if(null!=loginInfo) parameter.put("createBy", loginInfo.getUserId());
		}
	}

	@Override
	protected void beforeSetPropertiesForUpdate(Object domain, LoginInfo loginInfo) {
		if(domain instanceof EntityObject) {
			EntityObject entity = (EntityObject) domain;
			entity.setUpdateTime(new Date());
			if(null!=loginInfo) entity.setUpdateBy(loginInfo.getUserId());
		} else if(domain instanceof Map) {
			Map<String, Object> parameter = (Map<String, Object>) domain;
			parameter.put("updateTime", new Date());
			if(null!=loginInfo) parameter.put("updateBy", loginInfo.getUserId());
		}
		
	}

	@Override
	protected void beforeSetPropertiesForDelete(Object domain, LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		
	}
	
	public Integer deleteByIds(Set<?> ids, LoginInfo loginInfo) {
		return this.deleteByProps(MybatisConstants.PARAM_NAME_ID_ITER, ids);
	}
	
	public Integer deleteAll() {
		Map<String, Object> propsMap = new HashMap<>();
		propsMap.put("delAll", "delAll");
		return super.deleteBy(null, propsMap);
	}
	
}

