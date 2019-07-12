package com.yffd.bcap.uamc.domain.model.user;

import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.uamc.domain.model.account.AccountData;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;

import java.util.List;

public class UserService {

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

    public void delete(UserRepo repo) {
        this.validateAndThrow(repo);
        //1.删除用户与组的关联关系；
        //TODO
        //2.删除用户与角色的关联关系；
        //3.删除用户与权限的关联关系；
        //4.删除用户账号；
    }

    private void validateAndThrow(UserRepo userRepo) {
        if (null == userRepo) throw BcapValidateException.ERROR_PARAMS("资源库不能为空[roleRepo]");
    }
}
