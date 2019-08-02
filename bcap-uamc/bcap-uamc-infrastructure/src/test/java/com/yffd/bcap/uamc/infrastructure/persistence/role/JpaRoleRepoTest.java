package com.yffd.bcap.uamc.infrastructure.persistence.role;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.JpaRoleRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.JpaRoleRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

public class JpaRoleRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaRoleRepo jpaRoleRepo;

    @Autowired
    private JpaRoleRepoPlus jpaRoleRepoPlus;

    @Test
    public void queryTest() {
        RoleData roleData = new RoleData();
        roleData.setRoleId("qqq");
        roleData.setRoleName("test");
        Example<RoleData> example = Example.of(roleData);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher.withMatcher("roleId", ExampleMatcher.GenericPropertyMatchers.exact());
        exampleMatcher.withMatcher("roleName", matcher -> matcher.contains());
        Example<RoleData> example1 = Example.of(roleData, exampleMatcher);

        List<RoleData> list = this.jpaRoleRepoPlus.findAll(example1);
    }

    @Test
    public void querySortTest() {
        RoleData roleData = new RoleData();
        Example<RoleData> example = Example.of(roleData);

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime", "roleName");
        Sort sort1 = Sort.by(Sort.Order.desc("createTime"), Sort.Order.asc("roleId"));

        List<RoleData> list = this.jpaRoleRepoPlus.findAll(example, sort1);
    }

    @Test
    public void queryPageTest() {
        RoleData roleData = new RoleData();
        Example<RoleData> example = Example.of(roleData);

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime", "roleName");

        Pageable pageable = PageRequest.of(0,20, sort);
        Page<RoleData> page = this.jpaRoleRepoPlus.findAll(example, pageable);
        List<RoleData> list = new ArrayList<>();
        page.forEach(data -> list.add(data));
        System.out.println(list.size());
    }

    @Test
    public void insertOneTest() {
        RoleData roleData = new RoleData();
        roleData.setRoleName("测试");
        roleData.setRoleState(ActiveStateEnum.ACTIVE.getCode());
        roleData.setRemark("测试数据");
        DataObjectHelper.initPropsForAdd(roleData, sysOperator());
        this.jpaRoleRepo.insertOne(roleData);
        System.out.println("角色ID：" + roleData.getRoleId());
    }

    @Test
    public void insertXxxTest() {
        for (int i = 100; i<125; i++) {
            RoleData roleData = new RoleData();
            roleData.setRoleName("测试-" + i);
            roleData.setRoleState(ActiveStateEnum.ACTIVE.getCode());
            roleData.setRemark("测试数据");
            DataObjectHelper.initPropsForAdd(roleData, sysOperator());
            this.jpaRoleRepo.insertOne(roleData);
        }
    }

    @Test
    public void updateByIdTest() {
        RoleData roleData = new RoleData();
        roleData.setRoleId("BE144182AC884BB6962F5E5AA20DF2CA");
        roleData.setRoleName("测试");
//        roleData.setRoleState(ActiveStateEnum.DEACTIVE.getCode());
//        roleData.setRemark("测试数据");
        this.jpaRoleRepo.updateById(roleData);
        System.out.println(roleData.getRoleState());
    }

    @Test
    public void findByIdTest() {
        String id = "16F7C2B37C2846449F39F394E87DDFB4";
        RoleData ret = this.jpaRoleRepo.findById(id);
        System.out.println(ret);
        System.out.println(ret.getRoleName());
    }

    @Test
    public void deleteByIdTest() {
        String id ="11A3AE9217B849F49D44BF7D86A8D9FD";
        this.jpaRoleRepo.deleteById(id);
    }

    @Test
    public void updateRoleStateTest() {
        RoleData roleData = new RoleData();
        roleData.setRoleId("16F7C2B37C2846449F39F394E87DDFB4");
        roleData.setRoleState(ActiveStateEnum.DEACTIVE.getCode());
        DataObjectHelper.initPropsForUpdate(roleData, sysOperator());
        this.jpaRoleRepo.updateRoleState(roleData);
    }
}
