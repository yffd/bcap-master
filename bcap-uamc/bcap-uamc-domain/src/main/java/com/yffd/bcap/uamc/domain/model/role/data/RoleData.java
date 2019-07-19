package com.yffd.bcap.uamc.domain.model.role.data;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_role")
public class RoleData extends DataObjectSupport {
    private static final long serialVersionUID = -2563742842078527305L;
    @Id
    private String roleId;//角色ID
    private String roleName;//角色名称
    private String roleState;//状态，启用=enabled、停用=disabled
    private String remark;//描述

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
