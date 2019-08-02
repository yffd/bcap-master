package com.yffd.bcap.uamc.infrastructure.persistence.permission;

import com.alibaba.fastjson.JSON;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.application.permission.dto.PermissionCriteria;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa.query.JpaPermissionQry;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaPermissionQryTest extends UamcApplicationTest {
    @Autowired
    private JpaPermissionQry jpaPermissionQry;

    @Test
    public void findPageTest() {
        PermissionCriteria criteria = new PermissionCriteria();
//        criteria.setPmsId("88F952AC07EC4CC5A1F8D960F0226E8F");
        criteria.setPmsName("权限");
        criteria.setPmsState(ActiveStateEnum.ACTIVE.getCode());
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<PermissionData> pageData = jpaPermissionQry.findPage(criteria, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }
}
