package com.yffd.bcap.uamc.domain.model.role.service;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.model.role.aggregate.RoleAgrgt;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
//    private RoleRepo roleRepo = mock(RoleRepo.class);
//    private RoleRepo roleRepo = spy(RoleRepo.class);

    /*@Mock
    private RoleRepo roleRepo;*/
    @Spy
    private RoleRepo roleRepo;
    private RoleService roleService = new RoleService();
    private SysOperator sysOperator = spy(SysOperator.class);

    @Before
    public void setup() {
        sysOperator.setOperatorId("junit-test");
        sysOperator.setOperatorName("单元测试");
        sysOperator.setOperateTime(Calendar.getInstance().getTime());
    }

    @Test
    public void addRoleTest() {
        /*RoleEntity roleEntity = spy(RoleEntity.class);
        roleEntity.setRoleId(roleRepo.nextIdentity());
        RoleAgrgt roleAgrgt = new RoleAgrgt(sysOperator, roleEntity);

        doNothing().when(roleRepo).add(roleEntity);
        roleService.addRole(roleRepo, roleAgrgt);

        System.out.println(roleEntity.getRoleId());
        System.out.println(roleAgrgt.getSysOperator().getOperatorId());*/
    }
}
