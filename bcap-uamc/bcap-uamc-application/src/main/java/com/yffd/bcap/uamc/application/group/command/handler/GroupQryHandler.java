package com.yffd.bcap.uamc.application.group.command.handler;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.group.command.GroupCriteriaVo;
import com.yffd.bcap.uamc.application.group.command.GroupTreeVo;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.user.UserData;

import java.util.List;

public class GroupQryHandler {

    private GroupRepo groupRepo;

    List<GroupTreeVo> listTree(GroupCriteriaVo criteria) {
        //TODO
        return null;
    }

    /**
     * 查询所拥有的用户（直接用户）
     * @param pageInfo
     * @return
     */
    public PageData<UserData> hasDirectUsers(PageInfo pageInfo) {
        //TODO
        return null;
    }

    /**
     * 查询所拥有的所有用户（汇总用户）
     * @param pageInfo
     * @return
     */
    public PageData<UserData> hasUsers(PageInfo pageInfo) {
        //TODO
        return null;
    }

    public PageData<UserData> hasRoles(PageInfo pageInfo) {
        //TODO
        return null;
    }

    public PageData<PermissionData> hasDirectPermissions(PageInfo pageInfo) {
        //TODO
        return null;
    }

    public PageData<PermissionData> hasPermissions(PageInfo pageInfo) {
        //TODO
        return null;
    }
}
