package com.yffd.bcap.common.ddd.domain.data;

import com.yffd.bcap.common.model.system.SysOperator;

import java.util.Calendar;

public class DataObjectHelper {

    private DataObjectHelper() {
    }

    public static <T extends DataObject> void initPropsForAdd(T data, SysOperator sysOperator) {
        data.setVersion(0);
        data.setDelFlag("0");
        data.setCreateTime(Calendar.getInstance().getTime());
        if(null!=sysOperator) data.setCreateBy(sysOperator.getOperatorId());
    }

    public static <T extends DataObject> void initPropsForUpdate(T data, SysOperator sysOperator) {
        data.setVersion(data.getVersion() + 1);
        data.setUpdateTime(Calendar.getInstance().getTime());
        if(null!=sysOperator) data.setUpdateBy(sysOperator.getOperatorId());
    }

    /**
     * 逻辑删除
     * @param data
     * @param sysOperator
     * @param <T>
     */
    public static <T extends DataObject> void initPropsForLogicalDelete(T data, SysOperator sysOperator) {
        initPropsForUpdate(data, sysOperator);
        data.setDelFlag("1");
    }
}
