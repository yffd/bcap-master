package com.yffd.bcap.common.ddd.domain.entity.support;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.login.LoginInfo;
import com.yffd.bcap.common.model.system.SysOperator;

import java.util.Calendar;

public class EntitySupport {

    private EntitySupport() {
    }

    public static <T extends EntityObject> void initPropsForAdd(T entity, SysOperator sysOperator) {
        entity.setVersion(0);
        entity.setDelFlag("0");
        entity.setCreateTime(Calendar.getInstance().getTime());
        if(null!=sysOperator) entity.setCreateBy(sysOperator.getOperatorId());
    }

    public static <T extends EntityObject> void initPropsForUpdate(T entity, SysOperator sysOperator) {
        entity.setUpdateTime(Calendar.getInstance().getTime());
        if(null!=sysOperator) entity.setUpdateBy(sysOperator.getOperatorId());
    }

    /**
     * 逻辑删除
     * @param entity
     * @param sysOperator
     * @param <T>
     */
    public static <T extends EntityObject> void initPropsForLogicalDelete(T entity, SysOperator sysOperator) {
        initPropsForUpdate(entity, sysOperator);
        entity.setDelFlag("1");
    }
}
