package com.yffd.bcap.uamc.infrastructure.persistence.role;

import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaRoleRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaRoleRepo jpaRoleRepo;

    @Test
    public void insertOneTest() {
        RoleData roleData = new RoleData();
        roleData.setRoleName("测试");
        roleData.setRoleState(ActiveStateEnum.ACTIVE.getCode());
        roleData.setRemark("测试数据");
        this.jpaRoleRepo.insertOne(roleData);
        System.out.println("角色ID：" + roleData.getRoleId());
    }

    @Test
    public void updateByIdTest() {
        RoleData roleData = new RoleData();
        roleData.setRoleId("11A3AE9217B849F49D44BF7D86A8D9FD1");
//        roleData.setRoleName("测试");
        roleData.setRoleState(ActiveStateEnum.DEACTIVE.getCode());
//        roleData.setRemark("测试数据");
        this.jpaRoleRepo.updateById(roleData);
        System.out.println(roleData.getRoleState());
    }
}
