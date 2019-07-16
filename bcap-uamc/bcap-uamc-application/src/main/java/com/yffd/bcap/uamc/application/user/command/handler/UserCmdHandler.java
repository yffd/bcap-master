package com.yffd.bcap.uamc.application.user.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.uamc.domain.model.account.AccountRepo;
import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.domain.model.user.UserEntity;
import com.yffd.bcap.uamc.domain.model.user.UserRepo;
import com.yffd.bcap.uamc.domain.model.user.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public class UserCmdHandler {

    private UserRepo userRepo;
    private GroupUserRltRepo groupUserRltRepo;
    private RoleUserRltRepo roleUserRltRepo;
    private PmsUserRltRepo pmsUserRltRepo;
    private AccountRepo accountRepo;

    public void addUser(UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        if (exsistById(userEntity))
            throw BcapValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ userData.getUserId() +", class："+ userData.getClass() +"]");
        userRepo.insertOne(userEntity.add());
    }

    public void updateGroup(UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        userRepo.updateById(userEntity.updateById());
    }

    @Transactional
    public void deleteUser(UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        UserService userService = new UserService();
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
            throw BcapValidateException.ERROR("用户指派组失败，用户ID不存在["+ userData.getUserId() +"]");
        groupUserRltRepo.addRlt(userEntity.assignToGroups(groupIds));
    }

    /**
     * 解除已指派组
     * @param groupIds
     * @param userData
     * @param sysOperator
     */
    public void deleteRltGroups(Set<String> groupIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        groupUserRltRepo.deleteRlt(userEntity.removeRltGroups(groupIds));
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
            throw BcapValidateException.ERROR("用户指派角色失败，用户ID不存在["+ userData.getUserId() +"]");
        roleUserRltRepo.addRlt(userEntity.assignToRoles(roleIds));
    }

    /**
     * 解除已指派角色
     * @param roleIds
     * @param userData
     * @param sysOperator
     */
    public void deleteRltRoles(Set<String> roleIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        roleUserRltRepo.deleteRlt(userEntity.removeRltRoles(roleIds));
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
            throw BcapValidateException.ERROR("用户指派权限失败，用户ID不存在["+ userData.getUserId() +"]");
        pmsUserRltRepo.addRlt(userEntity.assignToPermissions(pmsIds));
    }

    /**
     * 解除已指派权限
     * @param pmsIds
     * @param userData
     * @param sysOperator
     */
    public void deleteRltPermissions(Set<String> pmsIds, UserData userData, SysOperator sysOperator) {
        UserEntity userEntity = new UserEntity(userData, sysOperator);
        pmsUserRltRepo.addRlt(userEntity.removeRltPermissions(pmsIds));
    }

}
