package com.yffd.bcap.uamc.domain.entities;

import com.yffd.bcap.common.domain.model.entity.EntityObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UamcRole extends EntityObject {
    private static final long serialVersionUID = -2563742842078527305L;
    @Id
    private String roleId;//角色ID
    private String roleName;//角色名称
    private String roleState;//状态，启用=enabled、停用=disabled
    private String remark;//描述
}
