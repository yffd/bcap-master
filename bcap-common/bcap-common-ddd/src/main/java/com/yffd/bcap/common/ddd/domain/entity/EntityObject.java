package com.yffd.bcap.common.ddd.domain.entity;

import com.yffd.bcap.common.ddd.domain.data.DataObject;
import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.common.model.exception.CheckException;
import com.yffd.bcap.common.model.generator.IdentityGenerator;
import com.yffd.bcap.common.model.system.SysOperator;

public abstract class EntityObject<D extends DataObject> implements IEntityObject {
    private SysOperator sysOperator;
    private D data;

    public EntityObject(D data, SysOperator sysOperator) {
        if (null == data || null == sysOperator)
            throw CheckException.PARAM_IS_EMPTY("构造器参数错误["+this.getClass()+"]");
        this.data = data;
        this.sysOperator = sysOperator;
    }

    public abstract String identity();

    public String nextIdentity() {
        return IdentityGenerator.getId();
    }

    public D data() {
        return this.data;
    }

    public SysOperator sysOperator() {
        return this.sysOperator;
    }

    public D add() {
        DataObjectHelper.initPropsForAdd(this.data, this.sysOperator);
        return this.data;
    }

    public D updateById() {
        if (this.isEmptyString(this.identity()))
            throw CheckException.PARAM_IS_EMPTY("修改失败，数据实体ID不能为空[" + this.getClass() + "]");
        DataObjectHelper.initPropsForUpdate(this.data, this.sysOperator);
        return this.data;
    }

    public String deleteById() {
        if (this.isEmptyString(this.identity()))
            throw CheckException.PARAM_IS_EMPTY("删除失败，数据实体ID不能为空[" + this.getClass() + "]");
        return this.identity();
    }

    public String exsistById() {
        if (this.isEmptyString(this.identity()))
            throw CheckException.PARAM_IS_EMPTY("主键查询失败，数据实体ID不能为空[" + this.getClass() + "]");
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

    private boolean isEmptyString(String str) {
        return (null==str || "".equals(str.trim()));
    }
}
