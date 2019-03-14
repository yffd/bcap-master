package com.yffd.bcap.bpm.domain.vo;

import lombok.Data;

@Data
public class ProcessDeploymentSaveVo extends ProcessDeploymentVo {
    private static final long serialVersionUID = -4296478218525525396L;
    /** 流程定义的bpmn文件名*/
    private String bpmnFileName;
    /** 流程定义的bpmn的图片文件名*/
    private String dgrmFileName;
    /** 流程定义的zip文件名，优先使用zip方式发布流程定义*/
    private String zipFileName;
}
