package com.yffd.bcap.uamc.infrastructure.persistence.group.jpa;

import com.alibaba.fastjson.JSON;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.application.group.dto.GroupCriteria;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.infrastructure.persistence.group.jpa.query.JpaGroupQry;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaGroupQryTest extends UamcApplicationTest {
    @Autowired
    private JpaGroupQry jpaGroupQry;

    @Test
    public void findPageTest() {
        GroupCriteria criteria = new GroupCriteria();
        criteria.setGroupName("组");
        criteria.setGroupState(ActiveStateEnum.ACTIVE.getCode());
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<GroupData> pageData = jpaGroupQry.findPage(criteria, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findUsersByGroupIdTest(){
        String groupId = "0484550B5FA64AA6A3DD23F69127DDE7";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<UserData> pageData = jpaGroupQry.findUsersByGroupId(groupId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findRolesByGroupIdTest() {
        String groupId = "7A31021316F14B79858E79A57CA5E2C2";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<RoleData> pageData = jpaGroupQry.findRolesByGroupId(groupId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findPermissionsByGroupIdTest() {
        String groupId = "7A31021316F14B79858E79A57CA5E2C2";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<PermissionData> pageData = jpaGroupQry.findPermissionsByGroupId(groupId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findSecondPermissionsByGroupIdTest(){
        String groupId = "7A31021316F14B79858E79A57CA5E2C2";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<PermissionData> pageData = jpaGroupQry.findSecondPermissionsByGroupId(groupId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }
}
