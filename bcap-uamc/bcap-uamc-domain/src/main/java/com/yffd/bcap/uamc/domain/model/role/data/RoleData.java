package com.yffd.bcap.uamc.domain.model.role.data;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class RoleData extends DataObjectSupport {
    private static final long serialVersionUID = -2563742842078527305L;
    @Id
    private String roleId;//角色ID
    private String roleName;//角色名称
    private String roleState;//状态，启用=enabled、停用=disabled
    private String remark;//描述
}
