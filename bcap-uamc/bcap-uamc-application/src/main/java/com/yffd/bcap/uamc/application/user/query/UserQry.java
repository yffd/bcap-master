package com.yffd.bcap.uamc.application.user.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.user.dto.UserCondition;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

public interface UserQry {

    PageData<UserData> findPage(UserCondition condition, PageInfo pageInfo);

    PageData<RoleData> findRolesByUserId(String userId, PageInfo pageInfo);

    PageData<RoleData> findSecondRolesByUserId(String userId, PageInfo pageInfo);

    PageData<PermissionData> findPermissionsByUserId(String userId, PageInfo pageInfo);

    PageData<PermissionData> findSecondPermissionsByUserId(String userId, PageInfo pageInfo);
}
