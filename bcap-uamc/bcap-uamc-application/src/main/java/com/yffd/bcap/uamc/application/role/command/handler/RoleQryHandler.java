package com.yffd.bcap.uamc.application.role.command.handler;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.role.command.RoleCriteriaCmd;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

public interface RoleQryHandler {

    PageData<RoleData> listPage(RoleCriteriaCmd roleCriteriaCmd);

    PageData<GroupData> hasGroups(String roleId, PageInfo pageInfo);

    PageData<PermissionData> hasPermissions(String roleId, PageInfo pageInfo);

    PageData<UserData> hasUsers(String roleId, PageInfo pageInfo);
}
