package com.yffd.bcap.bpm.core.service.impl;

import com.yffd.bcap.bpm.core.JunitBaseTest;
import com.yffd.bcap.bpm.domain.vo.ProcessDeploymentVo;
import com.yffd.bcap.bpm.domain.vo.ProcessInstanceVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

public class ProcessInstanceServiceImplTest extends JunitBaseTest {

    @Autowired
    private ProcessInstanceServiceImpl processInstanceServiceImpl;

    @Test
    public void startProcessTest() {
        String definitionKey = "helloworld";
        String businessKey = "test";
        String operator = "sys_11";
        Map<String, Object> variables = null;
        ProcessInstanceVo vo = this.processInstanceServiceImpl.startProcess(definitionKey, businessKey, operator, variables);

        System.out.println("流程实例ID\t\t业务关键字\t\t是否挂起\t\t是否结束\t\t发起时间\t\t发起人ID\t\t流程发布ID\t\t流程定义ID\t\t流程定义关键字\t\t流程定义版本");
        System.out.println(
                vo.getInstanceId()
                        + "\t\t" + vo.getBusinessKey()
                        + "\t\t" + vo.getIsSuspended()
                        + "\t\t" + vo.getIsEnded()
                        + "\t\t" + vo.getStartTime()
                        + "\t\t" + vo.getStartUserId()
                        + "\t\t" + vo.getDeployId()
                        + "\t\t" + vo.getDefinitionId()
                        + "\t\t" + vo.getDefinitionKey()
                        + "\t\t" + vo.getDefinitionVersion()
        );
    }

    @Test
    public void stopProcessTest() {
        String instanceId = "20001";
        String operator = "sys_stop";
        String reason = "逗你玩";
        processInstanceServiceImpl.stopProcess(instanceId, operator, reason);
    }

    @Test
    public void suspendProcessTest() {
        String instanceId = "20001";
        processInstanceServiceImpl.suspendProcess(instanceId);
    }

    @Test
    public void activeProcessTest() {
        String instanceId = "20001";
        processInstanceServiceImpl.activeProcess(instanceId);
    }
}


