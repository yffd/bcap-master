package com.yffd.bcap.uamc.domain.entities;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import lombok.Data;

@Data
public class UamcRsFile extends EntityObject {
    private static final long serialVersionUID = 6703844294322972286L;
    private String fileId;//文件ID
    private String fileName;//文件名称
    private String filePosition;//文件位置
}
