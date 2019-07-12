package com.yffd.bcap.uamc.application.group.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.group.GroupEntity;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import org.springframework.transaction.annotation.Transactional;

public class GroupCmdHandler {
    private GroupRepo groupRepo;

    @Transactional
    public void deleteGroupWithRlt(GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        this.groupRepo.deleteById(groupEntity.deleteById());
        //1.若有子组，则子组一起删除；
        //TODO
        //2.删除组与角色的关联关系；
        //3.删除组与权限的关联关系；
        //4.删除组与用户的关联关系；
    }

}
