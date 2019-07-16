package com.yffd.bcap.uamc.application.user.command.handler;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.user.command.UserCriteriaVo;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

public interface UserQryHandler {

    PageData<UserData> listPage(UserCriteriaVo criteria, PageInfo pageInfo);

    PageData<RoleData> hasDirectRoles(String userId, PageInfo pageInfo);

    PageData<RoleData> hasRoles(String userId, PageInfo pageInfo);

    PageData<RoleData> hasDirectPermissions(String userId, PageInfo pageInfo);

    PageData<RoleData> hasPermissions(String userId, PageInfo pageInfo);
}
