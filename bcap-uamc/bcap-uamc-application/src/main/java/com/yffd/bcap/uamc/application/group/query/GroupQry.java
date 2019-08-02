package com.yffd.bcap.uamc.application.group.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.group.dto.GroupCriteria;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

import java.util.List;

public interface GroupQry {

    PageData<GroupData> findPage(GroupCriteria criteria, PageInfo pageInfo);

    PageData<UserData> findUsersByGroupId(String groupId, PageInfo pageInfo);

    PageData<RoleData> findRolesByGroupId(String groupId, PageInfo pageInfo);

    PageData<PermissionData> findPermissionsByGroupId(String groupId, PageInfo pageInfo);

    PageData<PermissionData> findSecondPermissionsByGroupId(String groupId, PageInfo pageInfo);
}
