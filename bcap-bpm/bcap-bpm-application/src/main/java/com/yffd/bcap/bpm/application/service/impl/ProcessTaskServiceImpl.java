package com.yffd.bcap.bpm.application.service.impl;

import com.yffd.bcap.common.core.utils.EasyStringUtils;
import com.yffd.bpm.infrastructure.exception.BpmException;
import org.flowable.engine.TaskService;
import org.flowable.engine.common.api.FlowableObjectNotFoundException;
import org.flowable.engine.common.api.FlowableTaskAlreadyClaimedException;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ProcessTaskServiceImpl {

    @Autowired
    private TaskService taskService;

    /**
     * 认领任务，当任务分配给一组人员时，需要人员主动申领任务，任务申领成功后，该任务属于个人任务
     * @param taskId
     * @param userId
     */
    public void claimTask(String taskId, String userId) {
        if (EasyStringUtils.isBlank(taskId) || EasyStringUtils.isBlank(userId))
            throw BpmException.PARAMS_IS_EMPTY();
        try {
            taskService.claim(taskId, userId);
        } catch (FlowableObjectNotFoundException e) {
            throw BpmException.BIZ_ERROR("任务[" + taskId + "]不存在");
        } catch (FlowableTaskAlreadyClaimedException e) {
            throw BpmException.BIZ_ERROR("任务[\" + taskId + \"]已被认领");
        }
    }

    /**
     * 取消认领任务
     * @param taskId    任务ID
     */
    public void unclaimTask(String taskId) {
        if (EasyStringUtils.isBlank(taskId))
            throw BpmException.PARAMS_IS_EMPTY();
        try {
            taskService.unclaim(taskId);
        } catch (FlowableObjectNotFoundException e) {
            throw BpmException.BIZ_ERROR("任务[" + taskId + "]不存在");
        }
    }

    /**
     * 完成任务
     * @param taskId    任务ID
     * @param comment   批注
     * @param variables 变量
     */
    public void completeTask(String taskId, String comment, Map<String, Object> variables) {
        if (EasyStringUtils.isBlank(taskId))
            throw BpmException.PARAMS_IS_EMPTY();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (null == task) {
            throw BpmException.BIZ_ERROR("任务[" + taskId + "]不存在");
        }
        // 添加批注
        String processInstanceId = task.getProcessInstanceId();
        taskService.addComment(taskId, processInstanceId, comment);
        // 完成任务
        taskService.complete(taskId, variables);
    }

    /**
     * 认领任务并完成任务
     * @param taskId    任务ID
     * @param userId    任务认领人（任务操作人）
     * @param comment   批注
     * @param variables 变量
     */
    @Transactional
    public void claimAndCompleteTask(String taskId, String userId, String comment, Map<String, Object> variables) {
        this.claimTask(taskId, userId);
        this.completeTask(taskId, comment, variables);
    }

    /*public void suspendTask() {
    }

    public void activeTask() {

    }

    public void skipTask() {

    }*/
}

