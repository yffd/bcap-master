package com.yffd.bcap.uamc.application.organization.assembler;

import com.alibaba.fastjson.JSON;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.application.organization.dto.OrganizationNode;
import com.yffd.bcap.uamc.application.organization.query.OrganizationQry;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationAssemblerTest extends UamcApplicationTest {

    @Autowired
    private OrganizationQry organizationQry;

    @Test
    public void buildTreeTest() {
        List<OrganizationNode> dataList = organizationQry.findNodeList();
        OrganizationNode tree = OrganizationAssembler.buildTree(dataList);
        System.out.println(JSON.toJSONString(tree));
    }
}
