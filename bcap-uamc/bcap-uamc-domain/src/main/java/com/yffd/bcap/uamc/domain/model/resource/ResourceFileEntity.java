package com.yffd.bcap.uamc.domain.model.resource;

import com.yffd.bcap.common.ddd.domain.entity.EntityObjectSupport;
import com.yffd.bcap.common.model.system.SysOperator;

public class ResourceFileEntity extends EntityObjectSupport<ResourceMenuEntity> {

    public ResourceFileEntity(ResourceMenuEntity data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getMenuId();
    }
}
