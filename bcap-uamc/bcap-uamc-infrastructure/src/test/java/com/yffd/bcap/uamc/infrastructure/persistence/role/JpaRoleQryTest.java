package com.yffd.bcap.uamc.infrastructure.persistence.role;

import com.alibaba.fastjson.JSON;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.application.role.dto.RoleCriteria;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.infrastructure.persistence.group.jpa.JpaGroupRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.JpaRoleRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.query.JpaRoleQry;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class JpaRoleQryTest extends UamcApplicationTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private JpaRoleQry jpaRoleQry;

    @Test
    public void findListTest() throws ParseException {
        String start = "2019-06-20 17:27:23";
        String end = "2019-06-29 17:27:23";
        RoleCriteria criteria = new RoleCriteria();
//        criteria.setRoleName("测试");
//        criteria.setRoleState(ActiveStateEnum.ACTIVE.getCode());
        criteria.setStartTime(sdf.parse(start));
        criteria.setEndTime(sdf.parse(end));
        List<RoleData> list = jpaRoleQry.findList(criteria);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            printRole(list.get(i), i);
        }
    }

    @Test
    public void findPageTest() throws ParseException {
        String start = "2019-06-20 17:27:23";
        String end = "2019-06-29 17:27:23";
        RoleCriteria criteria = new RoleCriteria();
        criteria.setRoleName("测试");
//        criteria.setRoleState(ActiveStateEnum.ACTIVE.getCode());
//        criteria.setStartTime(sdf.parse(start));
//        criteria.setEndTime(sdf.parse(end));
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<RoleData> pageData = jpaRoleQry.findPage(criteria, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findGroupsByRoleIdTest() {
        String roleId = "16F7C2B37C2846449F39F394E87DDFB4";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<GroupData> pageData = jpaRoleQry.findGroupsByRoleId(roleId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findPermissionsByRoleIdTest() {
        String roleId = "16F7C2B37C2846449F39F394E87DDFB4";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<PermissionData> pageData = jpaRoleQry.findPermissionsByRoleId(roleId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    @Test
    public void findUsersByRoleIdTest() {
        String roleId = "16F7C2B37C2846449F39F394E87DDFB4";
        PageInfo pageInfo = new PageInfo(1, 10);
        PageData<UserData> pageData = jpaRoleQry.findUsersByRoleId(roleId, pageInfo);
        System.out.println(JSON.toJSONString(pageData.getPageInfo()));
        System.out.println(JSON.toJSONString(pageData.getData()));
    }

    public void printRole(RoleData data, int num) {
        System.out.println(num + "\t" + data.getRoleId() + "\t" + data.getRoleName() + "\t" + data.getRoleState() + "\t" + data.getCreateTime());
    }















    @Autowired
    private JpaRoleRepoPlus jpaRoleRepoPlus;
    @Autowired
    private JpaGroupRepoPlus jpaGroupRepoPlus;


    @Test
    public void findRolesByUserIdTest() {
        Pageable pageable = PageRequest.of(0,2);
        Page<RoleData> page = jpaRoleRepoPlus.findRolesByUserId("16F7C2B37C2846449F39F394E87DDFB4", pageable);
    }

    @Test
    public void hasGroupsTest() {
        Pageable pageable = PageRequest.of(0,2);
        Page<GroupData> page = jpaGroupRepoPlus.findPageByRoleId("16F7C2B37C2846449F39F394E87DDFB4", pageable);
        System.out.println(page.getContent().size());
        System.out.println(page.getTotalElements());
    }


    @Test
    public void queryTest() {
        LocalDateTime sLocalDate = LocalDateTime.now().minusYears(2);
        LocalDateTime eLocalDate = LocalDateTime.now();

        Instant instant = sLocalDate.atZone(ZoneId.systemDefault()).toInstant();
        Date sDate = Date.from(instant);
        Date eDate = Date.from(eLocalDate.atZone(ZoneId.systemDefault()).toInstant());

        Specification<RoleData> spec = new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.between(root.get("createTime"), sDate, eDate);
                return null;
            }
        };

        String roleName = "xiaozhang";
        spec = spec.and(new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("roleName"), "%"+ roleName +"%");
            }
        });

        String roleState = ActiveStateEnum.ACTIVE.getCode();
        spec = spec.and((root, query, builder) -> {return builder.equal(root.get("roleState"), roleState);});

        List<RoleData> list = jpaRoleRepoPlus.findAll(spec);
    }

    public void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }
}
