package com.yffd.bcap.uamc.application.role.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.role.dto.RoleCriteria;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

import java.util.List;

public interface RoleQry {

    List<RoleData> findList(RoleCriteria criteria);

    PageData<RoleData> findPage(RoleCriteria criteria, PageInfo pageInfo);

    /**
     * 查看相关联的组信息
     * @param roleId
     * @param pageInfo
     * @return
     */
    PageData<GroupData> findGroupsByRoleId(String roleId, PageInfo pageInfo);

    /**
     * 查看相关联的权限信息
     * @param roleId
     * @param pageInfo
     * @return
     */
    PageData<PermissionData> findPermissionsByRoleId(String roleId, PageInfo pageInfo);

    /**
     * 查看相关联的用户信息
     * @param roleId
     * @param pageInfo
     * @return
     */
    PageData<UserData> findUsersByRoleId(String roleId, PageInfo pageInfo);
}
