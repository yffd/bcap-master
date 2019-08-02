package com.yffd.bcap.uamc.domain.model.organization;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.system.SysOperator;

public class OrgEntity extends EntityObject<OrgData> {

    public OrgEntity(OrgData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getOrgId();
    }

}
