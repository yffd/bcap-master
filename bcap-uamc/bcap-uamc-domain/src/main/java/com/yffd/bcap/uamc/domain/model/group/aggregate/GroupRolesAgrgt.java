package com.yffd.bcap.uamc.domain.model.group.aggregate;

import com.yffd.bcap.common.ddd.domain.entity.aggregate.AggregateRoot;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.entities.UamcGroup;
import com.yffd.bcap.uamc.domain.entities.UamcRole;

import java.util.List;

public class GroupRolesAgrgt extends AggregateRoot<UamcGroup> {
    private static final long serialVersionUID = 6680074959041024398L;
    private List<UamcRole> roles;

    public GroupRolesAgrgt(SysOperator sysOperator, UamcGroup entity) {
        super(sysOperator, entity);
    }

    public GroupRolesAgrgt(SysOperator sysOperator, UamcGroup entity, List<UamcRole> roles) {
        super(sysOperator, entity);
        this.roles = roles;
    }
}
