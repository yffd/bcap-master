package com.yffd.bcap.uamc.domain.model.role.service;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
//    private RoleRepo roleRepo = mock(RoleRepo.class);
//    private RoleRepo roleRepo = spy(RoleRepo.class);

    /*@Mock
    private RoleRepo roleRepo;*/
    @Spy
    private RoleRepo roleRepo;
    private RoleService roleService = RoleService.getInstance();
    private SysOperator sysOperator = spy(SysOperator.class);

    @Before
    public void setup() {
        sysOperator.setUserId("junit-test");
        sysOperator.setUserName("单元测试");
        sysOperator.setOperateTime(Calendar.getInstance().getTime());
    }

    @Test
    public void addRoleTest() {
        RoleData roleData = spy(RoleData.class);
        roleData.setRoleId(roleRepo.nextIdentity());
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);

        doNothing().when(roleRepo).insertOne(roleData);
        roleRepo.insertOne(roleData);

        System.out.println(roleData.getRoleId());
        System.out.println(sysOperator.getUserId());
    }
}
