package com.yffd.bcap.uamc.domain.model.resource;

import lombok.Data;

@Data
public class ResourceFileData extends ResourceEntity {
    private static final long serialVersionUID = -5303092986997212899L;
    private String fileId;//文件ID
    private String fileName;//文件名称
    private String filePosition;//文件位置
}
