package com.yffd.bcap.common.ddd.domain.entity.aggregate;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.system.SysOperator;
import lombok.Getter;

@Getter
public abstract class AggregateRoot<E extends EntityObject> implements IAgrgtRoot {
    private SysOperator sysOperator;
    private E entity;

    public AggregateRoot(SysOperator sysOperator, E entity) {
        this.sysOperator = sysOperator;
        this.entity = entity;
    }
}
