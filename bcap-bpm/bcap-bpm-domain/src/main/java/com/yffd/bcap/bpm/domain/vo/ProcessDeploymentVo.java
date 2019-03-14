package com.yffd.bcap.bpm.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProcessDeploymentVo implements Serializable {
    private static final long serialVersionUID = -5514709478079489512L;
    /** 流程发布主键*/
    private String deployId;
    /** 流程发布名称*/
    private String deployName;
    /** 流程发布关键字*/
    private String deployKey;
    /** 流程发布分类*/
    private String deployCategroy;
    /** 流程发布时间*/
    private Date deployTime;

}
