package com.yffd.bcap.bpm.core.service.impl;

import com.yffd.bcap.bpm.domain.vo.ProcessTaskVo;
import com.yffd.bcap.common.core.page.PageParam;
import com.yffd.bcap.common.core.page.PageResult;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessTaskQueryServiceImpl {

    @Autowired
    private TaskService taskService;

    public PageResult<ProcessTaskVo> findMyTask(String userId, int pageNum, int pageSize) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskAssignee(userId);

        long totalSize = taskQuery.count();
        List<Task> list = null;
        if (totalSize > 0) {
            list = taskQuery.orderByTaskCreateTime().desc().listPage(pageNum, pageSize);
        }
        return toPageResult(totalSize, list, pageNum, pageSize);
    }

    private PageResult<ProcessTaskVo> toPageResult(long totalSize, List<Task> list, int pageNum, int pageSize) {
        int totalRecord = Integer.parseInt(totalSize + "");
        PageParam pageParam = new PageParam(pageNum, pageSize);
        pageParam.setTotalRecord(totalRecord);

        List<ProcessTaskVo> recoredList = null;
        if (null != list) {
            if (null != list) {
                for (Task task : list) {
                    ProcessTaskVo vo = new ProcessTaskVo();
                    vo.setTaskId(task.getId());
                    vo.setTaskName(task.getName());
                    vo.setTaskOperator(task.getAssignee());
                    vo.setInstanceId(task.getProcessInstanceId());
                    vo.setExecutionId(task.getExecutionId());
                    vo.setDefinitionId(task.getProcessDefinitionId());
                    recoredList.add(vo);
                }
            }
        }
        // 转换成分页对象
        PageResult result = new PageResult();
        result.setPageParam(pageParam);
        result.setRecordList(recoredList);
        return result;
    }

}
