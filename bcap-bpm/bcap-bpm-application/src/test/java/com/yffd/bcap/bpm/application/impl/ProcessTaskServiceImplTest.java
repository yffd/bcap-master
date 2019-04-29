package com.yffd.bcap.bpm.application.impl;

import com.yffd.bcap.bpm.application.JunitBaseTest;
import com.yffd.bcap.bpm.application.service.impl.ProcessTaskServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessTaskServiceImplTest extends JunitBaseTest {

    @Autowired
    private ProcessTaskServiceImpl processTaskService;

    @Test
    public void claimTaskTest() {
        String taskId = "2506";
        String userId = "lisi";
        processTaskService.claimTask(taskId, userId);
    }
}
