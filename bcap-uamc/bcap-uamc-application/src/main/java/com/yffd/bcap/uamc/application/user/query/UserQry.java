package com.yffd.bcap.uamc.application.user.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.user.dto.UserCondition;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

public interface UserQry {

    PageData<UserData> findPage(UserCondition condition, PageInfo pageInfo);

    /**
     * 用户的直接角色
     * @param userId
     * @param pageInfo
     * @return
     */
    PageData<RoleData> findRolesByUserId(String userId, PageInfo pageInfo);

    /**
     * 用户的间接角色，通过组扩展来的角色
     * @param userId
     * @param pageInfo
     * @return
     */
    PageData<RoleData> findSecondRolesByUserId(String userId, PageInfo pageInfo);

    /**
     * 用户的直接权限
     * @param userId
     * @param pageInfo
     * @return
     */
    PageData<PermissionData> findPermissionsByUserId(String userId, PageInfo pageInfo);

    /**
     * 用户的间接权限，通过组扩展来的权限
     * @param userId
     * @param pageInfo
     * @return
     */
    PageData<PermissionData> findSecondPermissionsByUserId(String userId, PageInfo pageInfo);
}
