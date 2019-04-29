package com.yffd.bcap.uamc.domain.dataobject;

import com.yffd.bcap.common.support.model.DataObject;
import lombok.Data;

@Data
public class UamcResourceOperation extends DataObject {
    private static final long serialVersionUID = -2308268338934424105L;
    private String oprtnId;//操作ID
    private String oprtnName;//操作名称
    private String oprtnState;//状态，启用=enabled、停用=disabled
    private String oprtnUrl;//操作地址
    private Integer num;//排序号
    private String optnDesc;//描述

}
