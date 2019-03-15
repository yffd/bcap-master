package com.yffd.bcap.bpm.core.service.impl;

import com.yffd.bcap.bpm.core.JunitBaseTest;
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
