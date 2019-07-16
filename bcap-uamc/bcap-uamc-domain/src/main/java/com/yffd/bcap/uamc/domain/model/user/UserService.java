package com.yffd.bcap.uamc.domain.model.user;

import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.uamc.domain.model.account.AccountData;
import com.yffd.bcap.uamc.domain.model.account.AccountRepo;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;

import java.util.List;

public class UserService {

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
