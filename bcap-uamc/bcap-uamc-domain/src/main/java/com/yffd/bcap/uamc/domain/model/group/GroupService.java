package com.yffd.bcap.uamc.domain.model.group;

import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.uamc.domain.model.relation.GroupPmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;

import java.util.Map;
import java.util.Set;

public class GroupService {

    public void deleteGroupWithRlt(GroupEntity groupEntity, GroupRepo groupRepo, RoleGroupRltRepo roleGroupRltRepo,
                                   GroupPmsRltRepo groupPmsRltRepo, GroupUserRltRepo groupUserRltRepo) {
        String delGroupId = groupEntity.deleteById();
        //1.若有子组，则子组一起删除；
        groupRepo.deleteById(delGroupId);
        Set<String> groupChildrenIds = groupRepo.findChildrenIds(delGroupId);
        if (BcapCollectionUtils.isNotEmpty(groupChildrenIds))
            groupRepo.deleteByIdIn(groupChildrenIds);
        //2.删除组与角色的关联关系；
        roleGroupRltRepo.deleteRltByGroupId(delGroupId);
        //3.删除组与权限的关联关系；
        groupPmsRltRepo.deleteRltByGroupId(delGroupId);
        //4.删除组与用户的关联关系；
        groupUserRltRepo.deleteRltByGroupId(delGroupId);
    }

    /**
     * 添加关联关系（多对多），组：权限
     * @param rltMap    key:pmsId, value:groupId
     * @param groupPmsRltRepo
     */
    public void addRltToPermissions(Map<String, String> rltMap, GroupPmsRltRepo groupPmsRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String pmsId = entry.getKey();
            String groupId = entry.getValue();
            groupPmsRltRepo.addRlt(pmsId, groupId);
        }
    }

    /**
     * 移除关联关系（多对多），组：权限
     * @param rltMap    key:pmsId, value:groupId
     * @param groupPmsRltRepo
     */
    public void removeRltToPermissions(Map<String, String> rltMap, GroupPmsRltRepo groupPmsRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String pmsId = entry.getKey();
            String groupId = entry.getValue();
            groupPmsRltRepo.deleteRlt(pmsId, groupId);
        }
    }

    /**
     * 添加关联关系（多对多），组：角色
     * @param rltMap    key:roleId, value:groupId
     * @param roleGroupRltRepo
     */
    public void addRltToRoles(Map<String, String> rltMap, RoleGroupRltRepo roleGroupRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String roleId = entry.getKey();
            String groupId = entry.getValue();
            roleGroupRltRepo.addRlt(groupId, roleId);
        }
    }

    /**
     * 移除关联关系（多对多），组：角色
     * @param rltMap    key:roleId, value:groupId
     * @param roleGroupRltRepo
     */
    public void removeRltToRoles(Map<String, String> rltMap, RoleGroupRltRepo roleGroupRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String roleId = entry.getKey();
            String groupId = entry.getValue();
            roleGroupRltRepo.deleteRlt(groupId, roleId);
        }
    }
}
