package com.yffd.bcap.uamc.application.group.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.uamc.domain.exception.UamcDomainValidateException;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.group.GroupEntity;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import com.yffd.bcap.uamc.domain.model.group.GroupService;
import com.yffd.bcap.uamc.domain.model.relation.GroupPmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

public class GroupCmdHandler {
    private GroupRepo groupRepo;
    private RoleGroupRltRepo roleGroupRltRepo;
    private GroupPmsRltRepo groupPmsRltRepo;
    private GroupUserRltRepo groupUserRltRepo;
    private GroupService groupService = new GroupService();

    public void addGroup(GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        if (exsistById(groupEntity))
            throw UamcDomainValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ groupData.getGroupId() +", class："+ groupData.getClass() +"]");
        groupRepo.insertOne(groupEntity.add());
    }

    public void updateGroup(GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        groupRepo.updateById(groupEntity.updateById());
    }

    @Transactional
    public void deleteGroup(GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        groupService.deleteGroupWithRlt(groupEntity, groupRepo, roleGroupRltRepo, groupPmsRltRepo, groupUserRltRepo);
    }

    /**
     * 启用组
     * @param groupData
     * @param sysOperator
     */
    public void activeGroup(GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        groupRepo.updateById(groupEntity.active());
    }

    /**
     * 禁用角色
     * @param groupData
     * @param sysOperator
     */
    public void deactiveGroup(GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        groupRepo.updateById(groupEntity.deactive());
    }

    /**
     * 指派角色
     * @param roleIds
     * @param groupData
     * @param sysOperator
     */
    public void assignToRoles(Set<String> roleIds, GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        if (!exsistById(groupEntity))
            throw UamcDomainValidateException.ERROR("组指派角色失败，组ID不存在["+ groupData.getGroupId() +"]");
        Map<String, String> rltMap = groupEntity.mappingRltRole(roleIds);    //构建映射关系
        groupService.addRltToRoles(rltMap, roleGroupRltRepo);
    }

    /**
     * 解除已指派角色
     * @param roleIds
     * @param groupData
     * @param sysOperator
     */
    public void deleteRltRoles(Set<String> roleIds, GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        Map<String, String> rltMap = groupEntity.mappingRltRole(roleIds);    //构建映射关系
        groupService.removeRltToRoles(rltMap, roleGroupRltRepo);
    }

    /**
     * 指派权限
     * @param pmsIds
     * @param groupData
     * @param sysOperator
     */
    @Transactional
    public void assignToPermissions(Set<String> pmsIds, GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        if (!exsistById(groupEntity))
            throw UamcDomainValidateException.ERROR("组指派权限失败，组ID不存在["+ groupData.getGroupId() +"]");
        Map<String, String> rltMap = groupEntity.mappingRltPermission(pmsIds);    //构建映射关系
        groupService.addRltToPermissions(rltMap, groupPmsRltRepo);
    }

    /**
     * 解除已指派权限
     * @param pmsIds
     * @param groupData
     * @param sysOperator
     */
    public void deleteRltPermissions(Set<String> pmsIds, GroupData groupData, SysOperator sysOperator) {
        GroupEntity groupEntity = new GroupEntity(groupData, sysOperator);
        Map<String, String> rltMap = groupEntity.mappingRltPermission(pmsIds);
        groupService.removeRltToPermissions(rltMap, groupPmsRltRepo);    //构建映射关系
    }

    private Boolean exsistById(GroupEntity groupEntity) {
        return null != groupRepo.findById(groupEntity.exsistById());
    }

}
