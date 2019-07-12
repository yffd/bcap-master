package com.yffd.bcap.uamc.domain.model.resource;

import lombok.Data;

@Data
public class ResourceOperationData extends ResourceEntity {
    private static final long serialVersionUID = 7434329744873777586L;
    private String oprtnId;//操作ID
    private String oprtnName;//操作名称
    private String oprtnState;//状态，启用=enabled、停用=disabled
    private String oprtnUrl;//操作地址
    private Integer num;//排序号
    private String remark;//描述
}
