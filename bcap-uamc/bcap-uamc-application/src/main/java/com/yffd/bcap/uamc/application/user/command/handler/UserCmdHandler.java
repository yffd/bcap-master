package com.yffd.bcap.uamc.application.user.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.exception.UamcDomainValidateException;
import com.yffd.bcap.uamc.domain.model.account.AccountRepo;
import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.domain.model.user.UserEntity;
import com.yffd.bcap.uamc.domain.model.user.UserRepo;
import com.yffd.bcap.uamc.domain.model.user.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

public class UserCmdHandler {

    private UserRepo userRepo;
    private GroupUserRltRepo groupUserRltRepo;
    private RoleUserRltRepo roleUserRltRepo;
    private PmsUserRltRepo pmsUserRltRepo;
    private AccountRepo accountRepo;
    private UserService userService = new UserService();

    public void addUser(UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        if (exsistById(userEntity))
            throw UamcDomainValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ userData.getUserId() +", class："+ userData.getClass() +"]");
        userRepo.insertOne(userEntity.add());
    }

    public void updateGroup(UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        userRepo.updateById(userEntity.updateById());
    }

    @Transactional
    public void deleteUser(UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        userService.deleteUserWithRlt(userEntity, userRepo, groupUserRltRepo, roleUserRltRepo, pmsUserRltRepo, accountRepo);
    }

    private Boolean exsistById(UserEntity userEntity) {
        return null != userRepo.findById(userEntity.exsistById());
    }

    /**
     * 指派组
     * @param groupIds
     * @param userData
     * @param sysOperator
     */
    public void assignToGroups(Set<String> groupIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        if (!exsistById(userEntity))
            throw UamcDomainValidateException.ERROR("用户指派组失败，用户ID不存在["+ userData.getUserId() +"]");
        Map<String, String> rltMap = userEntity.mappingRltGroup(groupIds);    //构建映射关系
        userService.addRltToGroups(rltMap, groupUserRltRepo);
    }

    /**
     * 解除已指派组
     * @param groupIds
     * @param userData
     * @param sysOperator
     */
    public void deleteRltGroups(Set<String> groupIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        Map<String, String> rltMap = userEntity.mappingRltGroup(groupIds);    //构建映射关系
        userService.removeRltToGroups(rltMap, groupUserRltRepo);
    }

    /**
     * 指派角色
     * @param roleIds
     * @param userData
     * @param sysOperator
     */
    public void assignToRoles(Set<String> roleIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        if (!exsistById(userEntity))
            throw UamcDomainValidateException.ERROR("用户指派角色失败，用户ID不存在["+ userData.getUserId() +"]");
        Map<String, String> rltMap = userEntity.mappingRltRole(roleIds);    //构建映射关系
        userService.addRltToRoles(rltMap, roleUserRltRepo);
    }

    /**
     * 解除已指派角色
     * @param roleIds
     * @param userData
     * @param sysOperator
     */
    public void deleteRltRoles(Set<String> roleIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        Map<String, String> rltMap = userEntity.mappingRltRole(roleIds);    //构建映射关系
        userService.removeRltToRoles(rltMap, roleUserRltRepo);
    }

    /**
     * 指派权限
     * @param pmsIds
     * @param userData
     * @param sysOperator
     */
    public void assignToPermissions(Set<String> pmsIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        if (!exsistById(userEntity))
            throw UamcDomainValidateException.ERROR("用户指派权限失败，用户ID不存在["+ userData.getUserId() +"]");
        Map<String, String> rltMap = userEntity.mappingRltPermission(pmsIds);    //构建映射关系
        userService.addRltToPermissions(rltMap, pmsUserRltRepo);
    }

    /**
     * 解除已指派权限
     * @param pmsIds
     * @param userData
     * @param sysOperator
     */
    public void deleteRltPermissions(Set<String> pmsIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        Map<String, String> rltMap = userEntity.mappingRltPermission(pmsIds);    //构建映射关系
        userService.removeRltToPermissions(rltMap, pmsUserRltRepo);
    }

}
