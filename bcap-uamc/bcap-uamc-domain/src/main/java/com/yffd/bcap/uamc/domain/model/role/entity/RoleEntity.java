package com.yffd.bcap.uamc.domain.model.role.entity;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class RoleEntity extends EntityObject {
    private static final long serialVersionUID = -2563742842078527305L;
    @Id
    private String roleId;//角色ID
    private String roleName;//角色名称
    private String roleState;//状态，启用=enabled、停用=disabled
    private String remark;//描述
}
