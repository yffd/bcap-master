package com.yffd.bcap.uamc.infrastructure.persistence.organization;

import com.alibaba.fastjson.JSON;
import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.uamc.domain.constants.enums.OrgTypeEnum;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.infrastructure.persistence.organization.jpa.JpaOrgRepo;
import com.yffd.bcap.uamc.infrastructure.persistence.organization.jpa.JpaOrgRepoPlus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaOrgRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaOrgRepo jpaOrgRepo;
    @Autowired
    private JpaOrgRepoPlus orgJpa;

    @Test
    public void findChildrenTest() {
        String orgId = "BRANCH_0";
        List<OrgData> dataList = jpaOrgRepo.findChildren(orgId);
        System.out.println(dataList.size());
        System.out.println(JSON.toJSONString(dataList));
    }

    @Test
    public void countUsersTest() {
        Set<String> orgIds = new HashSet<>();
        orgIds.add("DEPT_CS0");
        orgIds.add("DEPT_CS1");
        int count = jpaOrgRepo.countUsers(orgIds);
        System.out.println(count);
    }

    @Test
    public void deleteByIdsTest() {
        Set<String> orgIds = new HashSet<>();
        orgIds.add("qq");
        orgIds.add("www");
        jpaOrgRepo.deleteByIds(orgIds);
    }

    @Test
    public void insertOneTest() {
        OrgData data = new OrgData();
        data.setOrgId("ceshi");
        data.setOrgName("测试");
        DataObjectHelper.initPropsForAdd(data, sysOperator());
        jpaOrgRepo.insertOne(data);
    }

    @Test
    public void insertRootTest() {
        OrgData root = new OrgData();
        root.setOrgId("HFJT");
        root.setOrgName("汉富集团");
        root.setShotName("汉富集团");
        root.setOrgType(OrgTypeEnum.COMPANY.getCode());
        root.setNum(0);
        root.setRemark("企业公司");
        root.setOrgPath(null);
        root.setParentId(null);
        DataObjectHelper.initPropsForAdd(root, sysOperator());
        jpaOrgRepo.insertOne(root);
    }

    @Test
    public void insertBranchTest() {
        String parentId = "HFJT";
        for (int i = 0; i < 3; i++) {
            String orgId = "BRANCH_" + i;
            OrgData data = new OrgData();
            data.setOrgId(orgId);
            data.setOrgName("汉富集团-分公司-" + i);
            data.setShotName("分公司-" + i);
            data.setOrgType(OrgTypeEnum.BRANCH.getCode());
            data.setNum(0);
            data.setRemark("企业公司");
            data.setOrgPath(parentId);
            data.setParentId(parentId);
            DataObjectHelper.initPropsForAdd(data, sysOperator());
            jpaOrgRepo.insertOne(data);
        }
    }

    @Test
    public void insertDeptTest() {
        for (int i = 2; i < 5; i++) {
            String parentId = "BRANCH_" + i;
            String orgId = "DEPT_CP" + i;
            OrgData data = new OrgData();
            data.setOrgId(orgId);
            data.setOrgName("汉富集团-分公司-" + i + "-产品部-" + i);
            data.setShotName("产品部-" + i);
            data.setOrgType(OrgTypeEnum.DEPT.getCode());
            data.setNum(i);
            data.setRemark("企业部们");
            data.setOrgPath("HFJT," + parentId);
            data.setParentId(parentId);
            DataObjectHelper.initPropsForAdd(data, sysOperator());
            jpaOrgRepo.insertOne(data);
        }
    }

   /* @Test
    public void findChildrenTest() {
        List<OrgData> ret = orgJpa.findChildren("");
        System.out.println(ret);
    }*/
}
