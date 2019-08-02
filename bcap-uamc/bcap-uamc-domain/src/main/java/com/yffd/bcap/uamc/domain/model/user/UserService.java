package com.yffd.bcap.uamc.domain.model.user;

import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.uamc.domain.model.account.AccountData;
import com.yffd.bcap.uamc.domain.model.account.AccountRepo;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserService {
    private static final UserService instance = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public void deleteUserWithRlt(UserEntity userEntity, UserRepo userRepo, GroupUserRltRepo groupUserRltRepo,
                                  RoleUserRltRepo roleUserRltRepo, PmsUserRltRepo pmsUserRltRepo, AccountRepo accountRepo) {
        String deleteUserId = userEntity.deleteById();
        userRepo.deleteById(deleteUserId);
        //1.删除用户与组的关联关系；
        groupUserRltRepo.deleteRltByUserId(deleteUserId);
        //2.删除用户与角色的关联关系；
        roleUserRltRepo.deleteRltByUserId(deleteUserId);
        //3.删除用户与权限的关联关系；
        pmsUserRltRepo.deleteRltByUserId(deleteUserId);
        //4.删除用户账号；
        accountRepo.deleteByUserId(deleteUserId);
    }

    /**
     * 添加关联关系（多对多），组：用户
     * @param rltMap    key:groupId, value:userId
     * @param groupUserRltRepo
     */
    public void addRltToGroups(Map<String, String> rltMap, GroupUserRltRepo groupUserRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String groupId = entry.getKey();
            String userId = entry.getValue();
            groupUserRltRepo.addRlt(userId, groupId);
        }
    }

    /**
     * 移除关联关系（多对多），组：用户
     * @param rltMap    key:groupId, value:userId
     * @param groupUserRltRepo
     */
    public void removeRltToGroups(Map<String, String> rltMap, GroupUserRltRepo groupUserRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String groupId = entry.getKey();
            String userId = entry.getValue();
            groupUserRltRepo.deleteRlt(userId, groupId);
        }
    }

    /**
     * 添加关联关系（多对多），角色：用户
     * @param rltMap    key:roleId, value:userId
     * @param roleUserRltRepo
     */
    public void addRltToRoles(Map<String, String> rltMap, RoleUserRltRepo roleUserRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String roleId = entry.getKey();
            String userId = entry.getValue();
            roleUserRltRepo.addRlt(userId, roleId);
        }
    }

    /**
     * 移除关联关系（多对多），角色：用户
     * @param rltMap    key:roleId, value:userId
     * @param roleUserRltRepo
     */
    public void removeRltToRoles(Map<String, String> rltMap, RoleUserRltRepo roleUserRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String roleId = entry.getKey();
            String userId = entry.getValue();
            roleUserRltRepo.deleteRlt(userId, roleId);
        }
    }

    /**
     * 添加关联关系（多对多），权限：用户
     * @param rltMap    key:pmsId, value:userId
     * @param pmsUserRltRepo
     */
    public void addRltToPermissions(Map<String, String> rltMap, PmsUserRltRepo pmsUserRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String pmsId = entry.getKey();
            String userId = entry.getValue();
            pmsUserRltRepo.addRlt(userId, pmsId);
        }
    }

    /**
     * 移除关联关系（多对多），权限：用户
     * @param rltMap    key:pmsId, value:userId
     * @param pmsUserRltRepo
     */
    public void removeRltToPermissions(Map<String, String> rltMap, PmsUserRltRepo pmsUserRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String pmsId = entry.getKey();
            String userId = entry.getValue();
            pmsUserRltRepo.deleteRlt(userId, pmsId);
        }
    }

    public Boolean exsistById(UserEntity userEntity, UserRepo userRepo) {
        return null != userRepo.findById(userEntity.exsistById());
    }

    public AccountData hasAccount(UserRepo repo) {
        //TODO
        return null;
    }

    public OrgData hasOrg(UserRepo repo) {
        //TODO
        return null;
    }

    public List<GroupData> hasGroups(UserRepo repo) {
        //TODO
        return null;
    }

    /**
     * 查询所拥有的角色（直接角色）
     * @param repo
     * @return
     */
    public List<RoleData> hasDirectRoles(UserRepo repo) {
        //TODO
        return null;
    }

    /**
     * 查询所拥有的所有角色（汇总角色）<br/>
     * 1.组所拥有的角色+直接拥有的角色
     * @param repo
     * @return
     */
    public List<RoleData> hasRoles(UserRepo repo) {
        //TODO
        return null;
    }

    /**
     * 查询所拥有权限（直接权限）
     * @param repo
     * @return
     */
    public List<PermissionData> hasDirectPermissions(UserRepo repo) {
        //TODO
        return null;
    }

    /**
     * 查询所拥有的所有权限（汇总权限）<br/>
     * 1.组所拥有的权限+角色所拥有的权限+用户直接拥有的权限
     * @param repo
     * @return
     */
    public List<PermissionData> hasPermissions(UserRepo repo) {
        //TODO
        return null;
    }

}
