package com.yffd.bcap.common.ddd.domain.entity;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;
import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.common.model.generator.IdentityGenerator;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.common.support.util.StringUtils;

public abstract class EntityObjectSupport<E extends DataObjectSupport> implements IEntityObject {
    private SysOperator sysOperator;
    private E data;

    public EntityObjectSupport(E data, SysOperator sysOperator) {
        if (null == data || null == sysOperator)
            throw BcapValidateException.ERROR_PARAMS("构造器参数错误[data:"+data+", sysOperator:"+sysOperator+"]");
        this.data = data;
        this.sysOperator = sysOperator;
    }

    public abstract String identity();

    public String nextIdentity() {
        return IdentityGenerator.getId();
    }

    public E data() {
        return this.data;
    }

    public SysOperator sysOperator() {
        return this.sysOperator;
    }

    public E add() {
        DataObjectHelper.initPropsForAdd(this.data, this.sysOperator);
        return this.data;
    }

    public E updateById() {
        if (StringUtils.isBlank(this.identity()))
            throw BcapValidateException.ERROR_PARAMS("修改失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        DataObjectHelper.initPropsForUpdate(this.data, this.sysOperator);
        return this.data;
    }

    public String deleteById() {
        if (StringUtils.isBlank(this.identity()))
            throw BcapValidateException.ERROR_PARAMS("删除失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        return this.identity();
    }

    public String exsistById() {
        if (StringUtils.isBlank(this.identity()))
            throw BcapValidateException.ERROR_PARAMS("主键查询失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        return this.identity();
    }


    protected void initPropsForAdd() {
        DataObjectHelper.initPropsForAdd(this.data, this.sysOperator);
    }
    protected void initPropsForUpdate() {
        DataObjectHelper.initPropsForUpdate(this.data, this.sysOperator);
    }
    protected void initPropsForLogicalDelete() {
        DataObjectHelper.initPropsForLogicalDelete(this.data, this.sysOperator);
    }
}