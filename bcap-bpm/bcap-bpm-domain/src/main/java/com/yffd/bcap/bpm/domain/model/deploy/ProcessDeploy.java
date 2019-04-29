package com.yffd.bcap.bpm.domain.model.deploy;

import lombok.Data;

import java.io.InputStream;
import java.util.Date;

@Data
public class ProcessDeploy {
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

    public ProcessDeploy() {
    }

    public ProcessDeploy(String deployId, String deployName, String deployKey, String deployCategroy, Date deployTime) {
        this.deployId = deployId;
        this.deployName = deployName;
        this.deployKey = deployKey;
        this.deployCategroy = deployCategroy;
        this.deployTime = deployTime;
    }

    public void deployByClasspath(String bpmnFileName, String dgrmFileName) {

    }

    public void deployByClasspath(String zipFileName) {

    }

    public void deployByInputStream(InputStream inputStream) {

    }

    /**
     * 删除流程定义，同时删除流程实例、历史流程实例、任务
     * @param deployId
     */
    public void deleteById(String deployId) {
    }

    /**
     * 删除流程定义，同时删除流程实例、历史流程实例、任务
     * @param deployId  流程发布ID
     * @param force
     *              false：非强制删除，只能删除没有启动的流程，如果流程启动，就会抛出异常<br/>
     *              true：强制删除，不管流程是否启动<br/>
     */
    public void deleteById(String deployId, boolean force) {
    }


    /**
     * 获取流程定义bpmn资源
     * @param deployId
     * @return
     */
    public InputStream loadBpmn(String deployId) {
        return this.loadResource(deployId, ".bpmn");
    }

    /**
     * 获取流程定义图片资源
     * @param deployId
     * @return
     */
    public InputStream loadDgrm(String deployId) {
        return this.loadResource(deployId, ".png");
    }

    /**
     * 获取流程定义资源，xml文件和png文件
     * @param deployId
     * @param resourceType  bpmn、png
     * @return
     */
    protected InputStream loadResource(String deployId, String resourceType) {
        String resourceName = null;
        // 获取图片资源名称
        /*List<String> list = repositoryService.getDeploymentResourceNames(deployId);
        if (null != list && list.size() > 0) {
            for (String name : list) {
                if(name.endsWith(resourceType)){
                    resourceName = name;
                }
            }
        }
        if (null != resourceName) {
            //获取图片的输入流
            InputStream inputStream = repositoryService.getResourceAsStream(deployId, resourceName);
            return inputStream;
        }*/
        return null;
    }
}
