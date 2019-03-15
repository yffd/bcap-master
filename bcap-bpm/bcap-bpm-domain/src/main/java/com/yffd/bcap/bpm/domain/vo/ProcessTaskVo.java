package com.yffd.bcap.bpm.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessTaskVo implements Serializable {
    private static final long serialVersionUID = -3912685592043379846L;

    private String taskId;          // 任务ID
    private String taskName;        // 任务名称
    private String taskOperator;    // 任务操作人-办理人
    private String taskStatus;      // 任务状态
    private String taskNodekey;     // 任务节点KEY
    private String instanceId;      // 流程实例ID
    private String executionId;     // 执行对象ID
    private String definitionId;    // 流程定义ID
}
