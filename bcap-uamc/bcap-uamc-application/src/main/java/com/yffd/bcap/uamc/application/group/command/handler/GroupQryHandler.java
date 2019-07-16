package com.yffd.bcap.uamc.application.group.command.handler;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.group.command.GroupCriteriaVo;
import com.yffd.bcap.uamc.application.group.command.GroupTreeVo;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

import java.util.List;

public interface GroupQryHandler {

    List<GroupTreeVo> listTree(GroupCriteriaVo criteria);

    /**
     * 查询所拥有的用户（直接用户）
     * @param pageInfo
     * @return
     */
    PageData<UserData> hasDirectUsers(PageInfo pageInfo);

    /**
     * 查询所拥有的所有用户（汇总用户）
     * @param pageInfo
     * @return
     */
    PageData<UserData> hasUsers(PageInfo pageInfo);

    PageData<UserData> hasRoles(PageInfo pageInfo);

    PageData<PermissionData> hasDirectPermissions(PageInfo pageInfo);

    PageData<PermissionData> hasPermissions(PageInfo pageInfo);
}
