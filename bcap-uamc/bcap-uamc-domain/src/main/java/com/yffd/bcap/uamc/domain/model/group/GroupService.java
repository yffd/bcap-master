package com.yffd.bcap.uamc.domain.model.group;

import com.yffd.bcap.common.support.util.CollectionUtils;
import com.yffd.bcap.uamc.domain.model.relation.GroupPmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;

import java.util.Set;

public class GroupService {

    public void deleteGroupWithRlt(GroupEntity groupEntity, GroupRepo groupRepo, RoleGroupRltRepo roleGroupRltRepo,
                                   GroupPmsRltRepo groupPmsRltRepo, GroupUserRltRepo groupUserRltRepo) {
        String delGroupId = groupEntity.deleteById();
        //1.若有子组，则子组一起删除；
        groupRepo.deleteById(delGroupId);
        Set<String> groupChildrenIds = groupRepo.findChildrenIds(delGroupId);
        if (CollectionUtils.isNotEmpty(groupChildrenIds))
            groupRepo.deleteByIdIn(groupChildrenIds);
        //2.删除组与角色的关联关系；
        roleGroupRltRepo.deleteRltByGroupId(delGroupId);
        //3.删除组与权限的关联关系；
        groupPmsRltRepo.deleteRltByGroupId(delGroupId);
        //4.删除组与用户的关联关系；
        groupUserRltRepo.deleteRltByGroupId(delGroupId);
    }

}
