package com.yffd.bcap.uamc.domain.entities;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;
import lombok.Data;

@Data
public class UamcGroup extends DataObjectSupport {
    private static final long serialVersionUID = 8917064942315854878L;
    private String groupId;//组ID
    private String groupName;//组名称
    private String parentId;//父组ID
    private String groupState;//状态，启用=enabled、停用=disabled
    private String remark;//组描述
}
