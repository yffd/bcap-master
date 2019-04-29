package com.yffd.bcap.uamc.domain.dataobject;

import com.yffd.bcap.common.support.model.DataObject;
import lombok.Data;

@Data
public class UamcRoleDO extends DataObject {
    private static final long serialVersionUID = -2563742842078527305L;
    private String roleId;//角色ID
    private String roleName;//角色名称
    private String roleState;//状态，启用=enabled、停用=disabled
    private String roleDesc;//描述
}
