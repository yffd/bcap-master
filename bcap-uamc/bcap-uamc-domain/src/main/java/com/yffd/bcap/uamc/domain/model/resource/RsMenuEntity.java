package com.yffd.bcap.uamc.domain.model.resource;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.system.SysOperator;

public class RsMenuEntity extends EntityObject<RsMenuData> {

    public RsMenuEntity(RsMenuData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getMenuId();
    }

    public String delRltPmsByMenuId() {
        return this.data().getMenuId();
    }
}
