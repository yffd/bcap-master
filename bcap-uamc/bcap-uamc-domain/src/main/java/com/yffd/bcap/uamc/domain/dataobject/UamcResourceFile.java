package com.yffd.bcap.uamc.domain.dataobject;

import com.yffd.bcap.common.support.model.DataObject;
import lombok.Data;

@Data
public class UamcResourceFile extends DataObject {
    private static final long serialVersionUID = 6703844294322972286L;
    private String fileId;//文件ID
    private String fileName;//文件名称
    private String filePosition;//文件位置
}
