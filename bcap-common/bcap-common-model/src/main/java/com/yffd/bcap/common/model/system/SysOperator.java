package com.yffd.bcap.common.model.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysOperator {
    private String operatorId;
    private String operatorName;
    private Date operateTime;
}
