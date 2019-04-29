package com.yffd.bcap.uamc.domain.dataobject;

import com.yffd.bcap.common.support.model.DataObject;
import lombok.Data;

@Data
public class UamcGroupDO extends DataObject {
    private static final long serialVersionUID = 8917064942315854878L;
    private String groupId;//组ID
    private String groupName;//组名称
    private String parentId;//父组ID
    private String groupState;//状态，启用=enabled、停用=disabled
    private String groupDesc;//组描述
}
