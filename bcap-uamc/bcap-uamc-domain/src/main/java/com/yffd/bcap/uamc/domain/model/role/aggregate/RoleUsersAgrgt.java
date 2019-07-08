package com.yffd.bcap.uamc.domain.model.role.aggregate;

import com.yffd.bcap.common.ddd.domain.entity.aggregate.AggregateRoot;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.user.entity.UserEntity;

import java.util.List;

public class RoleUsersAgrgt extends AggregateRoot<RoleEntity> {
    private static final long serialVersionUID = -4181008501602289345L;

    private List<UserEntity> users;

    public RoleUsersAgrgt(SysOperator sysOperator, RoleEntity entity) {
        super(sysOperator, entity);
    }

    public List<UserEntity> ownUsers() {
        return this.users;
    }
}
