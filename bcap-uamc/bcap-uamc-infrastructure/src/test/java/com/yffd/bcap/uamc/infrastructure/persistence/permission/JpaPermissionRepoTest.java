package com.yffd.bcap.uamc.infrastructure.persistence.permission;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.common.model.generator.IdentityGenerator;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.constants.enums.SourceTypeEnum;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaPermissionRepoTest extends UamcApplicationTest {
    @Autowired
    private PermissionRepo permissionRepo;

    @Test
    public void insertXxxTest() {
        for (int i = 100; i < 125; i++) {
            PermissionData data = new PermissionData();
            data.setPmsName("权限_" + i);
            data.setPmsState(ActiveStateEnum.ACTIVE.getCode());
            data.setPmsSourceId(IdentityGenerator.getId());
            data.setPmsSourceType(SourceTypeEnum.MENU.getCode());
            data.setRemark("测试");
            DataObjectHelper.initPropsForAdd(data, sysOperator());
            permissionRepo.insertOne(data);
        }
    }
}
