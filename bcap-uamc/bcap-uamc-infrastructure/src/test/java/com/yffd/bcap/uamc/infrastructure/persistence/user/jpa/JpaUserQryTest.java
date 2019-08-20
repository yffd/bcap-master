package com.yffd.bcap.uamc.infrastructure.persistence.user.jpa;

import com.alibaba.fastjson.JSON;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.application.user.dto.UserCondition;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.infrastructure.persistence.user.jpa.query.JpaUserQry;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class JpaUserQryTest extends UamcApplicationTest {
    @Autowired
    private JpaUserQry jpaUserQry;

    @Test
    public void findPageTest() throws ParseException {
        String start = "2019-04-20 17:27:23";
        String end = "2019-06-29 17:27:23";
        UserCondition criteria = new UserCondition();
        criteria.setUserName("用户");
//        criteria.setUserId("589F26598EAC44A99B58A31BAD37793E");
        criteria.setStartTime(SDF.parse(start));
        criteria.setEndTime(SDF.parse(end));
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<UserData> pageData = jpaUserQry.findPage(criteria, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findRolesByUserIdTest() {
        String userId = "C1F1C1373C824C5B801FF6AF2E900A42";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<RoleData> pageData = jpaUserQry.findRolesByUserId(userId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findSecondRolesByUserIdTest() {
        String userId = "C8AF5A9A188544A0A735920E02ADCAD0";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<RoleData> pageData = jpaUserQry.findSecondRolesByUserId(userId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findPermissionsByUserIdTest() {
        String userId = "C8AF5A9A188544A0A735920E02ADCAD0";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<PermissionData> pageData = jpaUserQry.findPermissionsByUserId(userId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findSecondPermissionsByUserIdTest() {
        String userId = "C8AF5A9A188544A0A735920E02ADCAD0";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<PermissionData> pageData = jpaUserQry.findSecondPermissionsByUserId(userId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

}
