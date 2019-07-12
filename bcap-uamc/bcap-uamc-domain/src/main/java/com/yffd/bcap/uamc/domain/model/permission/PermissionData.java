package com.yffd.bcap.uamc.domain.model.permission;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;
import lombok.Data;

@Data
public class PermissionData extends DataObjectSupport {
    private static final long serialVersionUID = -8198274109263931920L;
    private String pmsId;//权限ID
    private String pmsName;//权限名称
    private String pmsState;//状态，启用=enabled、停用=disabled
    private String pmsSourceType;//权限类型，菜单=menu、操作=operation、...
    private String pmsSourceId;//权限来源ID，指资源的ID
    private String remark;//描述
}
