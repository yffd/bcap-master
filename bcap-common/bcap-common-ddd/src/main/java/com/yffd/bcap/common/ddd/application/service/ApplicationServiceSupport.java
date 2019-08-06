package com.yffd.bcap.common.ddd.application.service;

import com.yffd.bcap.common.ddd.domain.data.DataObject;
import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.ddd.domain.repository.RepositorySupport;
import com.yffd.bcap.common.ddd.exception.DomainException;
import com.yffd.bcap.common.model.system.SysOperator;

public abstract class ApplicationServiceSupport<D extends DataObject> implements IApplicationService {

    public abstract EntityObject buildEntity(D data, SysOperator sysOperator);
    public abstract RepositorySupport repository();

    public void add(D data, SysOperator sysOperator) {
        EntityObject entity = this.buildEntity(data, sysOperator);
        if (this.exsistById(entity)) {
            throw DomainException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ entity.identity() +", class："+ data.getClass() +"]");
        }
        this.repository().insertOne(entity.add());
    }

    public void update(D data, SysOperator sysOperator) {
        EntityObject entity = this.buildEntity(data, sysOperator);
        this.repository().updateById(entity.updateById());
    }

    public void delete(D data, SysOperator sysOperator) {
        EntityObject entity = this.buildEntity(data, sysOperator);
        this.repository().deleteById(entity.deleteById());
    }

    protected Boolean exsistById(EntityObject entity) {
        return null != this.repository().findById(entity.exsistById());
    }
}
