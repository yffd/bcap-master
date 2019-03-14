package com.yffd.bcap.bpm.core.service.impl;

import com.yffd.bcap.bpm.core.exception.BpmException;
import com.yffd.bcap.bpm.domain.vo.ProcessDeploymentSaveVo;
import com.yffd.bcap.bpm.domain.vo.ProcessDeploymentVo;
import com.yffd.bcap.common.core.page.PageParam;
import com.yffd.bcap.common.core.page.PageResult;
import com.yffd.bcap.common.core.utils.EasyStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.DeploymentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@Service
public class ProcessDeployServiceImpl {
    @Autowired
    private RepositoryService repositoryService;

    public PageResult<ProcessDeploymentVo> findPage(String deployName, String deployKey, String deployCategory, int pageNum, int pageSize) {
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        if (EasyStringUtils.isNotBlank(deployName))
            deploymentQuery.deploymentNameLike("%"+deployName+"%");
        if (EasyStringUtils.isNotBlank(deployKey))
            deploymentQuery.deploymentKey(deployKey);
        if (EasyStringUtils.isNotBlank(deployCategory))
            deploymentQuery.deploymentCategory(deployCategory);

        long totalSize = deploymentQuery.count();
        int totalRecord = Integer.parseInt(totalSize + "");
        PageParam pageParam = new PageParam(pageNum, pageSize);
        pageParam.setTotalRecord(totalRecord);

        List<Deployment> list = null;
        if (totalSize > 0) {
            list = deploymentQuery.orderByDeploymenTime().desc()
                    .listPage(pageNum, pageSize);
        }
        // 转换成分页对象
        PageResult result = new PageResult();
        result.setPageParam(pageParam);
        if (null != list) {
            for (Deployment deployment : list) {
                ProcessDeploymentVo vo = new ProcessDeploymentVo();
                vo.setDeployId(deployment.getId());
                vo.setDeployName(deployment.getName());
                vo.setDeployCategroy(deployment.getCategory());
                result.getRecordList().add(vo);
            }
        }
        return result;
    }

    public ProcessDeploymentVo findByDeployId(String deployId) {
        Deployment deployment = repositoryService.createDeploymentQuery()
                .deploymentId(deployId)
                .singleResult();
        ProcessDeploymentVo vo = new ProcessDeploymentVo();
        vo.setDeployId(deployment.getId());
        vo.setDeployName(deployment.getName());
        vo.setDeployCategroy(deployment.getCategory());

        return vo;
    }

    public String deploy(ProcessDeploymentSaveVo processDeploymentSaveVo) {
        if (EasyStringUtils.isNotBlank(processDeploymentSaveVo.getZipFileName())) {
            return this.deploy(processDeploymentSaveVo.getDeployName(),
                    processDeploymentSaveVo.getDeployKey(),
                    processDeploymentSaveVo.getDeployCategroy(),
                    processDeploymentSaveVo.getZipFileName());
        }
        return this.deploy(processDeploymentSaveVo.getDeployName(),
                processDeploymentSaveVo.getDeployKey(),
                processDeploymentSaveVo.getDeployCategroy(),
                processDeploymentSaveVo.getBpmnFileName(),
                processDeploymentSaveVo.getDgrmFileName());
    }

    public String deploy(String deployName, String deployKey, String deployCategory, String bpmnFileName, String dgrmFileName) {
        if (EasyStringUtils.isBlank(deployName) ||
                (EasyStringUtils.isBlank(bpmnFileName) && EasyStringUtils.isBlank(dgrmFileName))) {
            throw BpmException.PARAMS_IS_EMPTY();
        }
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name(deployName);
        deploymentBuilder.key(deployKey);
        deploymentBuilder.category(deployCategory);
        deploymentBuilder.addClasspathResource(bpmnFileName);
        deploymentBuilder.addClasspathResource(dgrmFileName);

        Deployment deployment = deploymentBuilder.deploy();
        return deployment.getId();
    }

    public String deploy(String deployName, String deployKey, String deployCategory, String zipFileName) {
        if (StringUtils.isBlank(deployName)) {
            throw BpmException.PARAMS_IS_EMPTY();
        }
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name(deployName);
        deploymentBuilder.key(deployKey);
        deploymentBuilder.category(deployCategory);

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(zipFileName);
        ZipInputStream zipInputStream = new ZipInputStream(in);
        deploymentBuilder.addZipInputStream(zipInputStream);

        Deployment deployment = deploymentBuilder.deploy();
        return deployment.getId();
    }

    public String deploy(String deployName, String deployKey, String deployCategory, InputStream inputStream) {
        if (StringUtils.isBlank(deployName)) {
            throw BpmException.PARAMS_IS_EMPTY();
        }
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name(deployName);
        deploymentBuilder.key(deployKey);
        deploymentBuilder.category(deployCategory);
        deploymentBuilder.addZipInputStream(new ZipInputStream(inputStream));

        Deployment deployment = deploymentBuilder.deploy();
        return deployment.getId();
    }

    /**
     * 删除流程定义，同时删除流程实例、历史流程实例、任务
     * @param deployId
     */
    public void deleteByDeployId(String deployId) {
        this.deleteByDeployId(deployId, false);
    }

    /**
     * 删除流程定义，同时删除流程实例、历史流程实例、任务
     * @param deployId  流程发布ID
     * @param force
     *              false：非强制删除，只能删除没有启动的流程，如果流程启动，就会抛出异常<br/>
     *              true：强制删除，不管流程是否启动<br/>
     */
    public void deleteByDeployId(String deployId, boolean force) {
        repositoryService.deleteDeployment(deployId, force);
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
        List<String> list = repositoryService.getDeploymentResourceNames(deployId);
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
        }
        return null;
    }



}
