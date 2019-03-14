package com.yffd.bcap.bpm.core.service.impl;

import com.yffd.bcap.bpm.core.exception.BpmException;
import com.yffd.bcap.bpm.domain.vo.ProcessInstanceVo;
import com.yffd.bcap.common.core.utils.EasyStringUtils;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProcessInstanceServiceImpl {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 启动流程
     * @param definitionKey
     * @param businessKey
     * @param operator
     * @param variables
     * @return
     */
    public ProcessInstanceVo startProcess(String definitionKey, String businessKey, String operator, Map<String, Object> variables) {
        if (EasyStringUtils.isBlank(definitionKey) || EasyStringUtils.isBlank(businessKey))
            throw BpmException.PARAMS_IS_EMPTY();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(definitionKey).latestVersion().singleResult();
        if (null == processDefinition) {
            throw BpmException.BIZ_ERROR("流程模型[" + definitionKey + "]尚未发布或不可用");
        }
        if (processDefinition.isSuspended()) {
            throw BpmException.BIZ_ERROR("流程模型[" + definitionKey + "]已挂起，不能发布新流程");
        }
        // 设置流程启动人-操作人
        identityService.setAuthenticatedUserId(operator);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(definitionKey, businessKey, variables);
        if(processInstance == null){
            throw BpmException.BIZ_ERROR("流程发布失败");
        }

        return this.toVo(processInstance);
    }

    /**
     * 结束流程
     * @param instanceId
     * @param operator
     * @param reason
     */
    public void stopProcess(String instanceId, String operator, String reason) {
        if (EasyStringUtils.isBlank(instanceId) || EasyStringUtils.isBlank(operator) || EasyStringUtils.isBlank(reason))
            throw BpmException.PARAMS_IS_EMPTY();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        if (processInstance == null) {
            throw BpmException.BIZ_ERROR("流程实例不存在或已结束");
        }
        runtimeService.deleteProcessInstance(instanceId, reason);
    }

    /**
     * 挂起流程
     * @param instanceId
     */
    public void suspendProcess(String instanceId) {
        if (EasyStringUtils.isBlank(instanceId)) throw BpmException.PARAMS_IS_EMPTY();
        runtimeService.suspendProcessInstanceById(instanceId);
    }

    /**
     * 激活已挂起的流程
     * @param instanceId
     */
    public void activeProcess(String instanceId) {
        if (EasyStringUtils.isBlank(instanceId)) throw BpmException.PARAMS_IS_EMPTY();
        runtimeService.activateProcessInstanceById(instanceId);
    }

    private ProcessInstanceVo toVo(ProcessInstance processInstance) {
        ProcessInstanceVo vo = new ProcessInstanceVo();
        vo.setInstanceId(processInstance.getProcessInstanceId());
        vo.setBusinessKey(processInstance.getBusinessKey());
        vo.setIsSuspended(processInstance.isSuspended());
        vo.setIsEnded(processInstance.isEnded());
        vo.setStartUserId(processInstance.getStartUserId());
        vo.setStartTime(processInstance.getStartTime());
        vo.setDefinitionId(processInstance.getProcessDefinitionId());
        vo.setDefinitionKey(processInstance.getProcessDefinitionKey());
        vo.setDefinitionVersion(processInstance.getProcessDefinitionVersion());
        vo.setDeployId(processInstance.getDeploymentId());
        return vo;
    }
}
