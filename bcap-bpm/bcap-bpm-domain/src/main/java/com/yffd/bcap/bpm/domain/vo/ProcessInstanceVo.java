package com.yffd.bcap.bpm.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProcessInstanceVo implements Serializable {
    private static final long serialVersionUID = -4142422594758172864L;
    private String instanceId;      // 流程实例ID
    private String businessKey;     // 业务关键字：业务类型-业务ID
    private Boolean isSuspended;    // 是否挂起：1=激活、2=挂起
    private Boolean isEnded;        // 是否结束
    private Date startTime;         // 发起时间
    private String startUserId;     // 发起人ID

    private String deployId;        // 流程发布ID
    private String definitionId;    // 流程定义ID
    private String definitionKey;   // 流程定义关键字
    private Integer definitionVersion;  // 流程定义版本

}
