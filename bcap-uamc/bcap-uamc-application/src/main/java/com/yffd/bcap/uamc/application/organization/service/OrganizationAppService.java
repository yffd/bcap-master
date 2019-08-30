package com.yffd.bcap.uamc.application.organization.service;

import com.yffd.bcap.common.model.exception.InvalidException;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.application.organization.assembler.OrganizationAssembler;
import com.yffd.bcap.uamc.application.organization.dto.OrganizationNode;
import com.yffd.bcap.uamc.application.organization.query.OrganizationQry;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.organization.OrgEntity;
import com.yffd.bcap.uamc.domain.model.organization.OrgRepo;
import com.yffd.bcap.uamc.domain.model.organization.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationAppService {
    private OrgService orgService = OrgService.instance();
    @Autowired
    private OrgRepo orgRepo;
    @Autowired
    private OrganizationQry organizationQry;

    public OrganizationNode buildTree() {
        List<OrganizationNode> dataList = organizationQry.findNodeList();
        return OrganizationAssembler.buildTree(dataList);
    }

    public void addRoot(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        if (orgService.exsistOrgById(orgEntity, orgRepo))
            throw InvalidException.DATA_EXSIST("添加失败，数据已存在[ID: "+ orgData.getOrgId() +", class："+ orgData.getClass() +"]");
        orgRepo.insertOne(orgEntity.addRoot());
    }

    public void addChild(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        if (orgService.exsistOrgById(orgEntity, orgRepo))
            throw InvalidException.DATA_EXSIST("添加失败，数据已存在[ID: "+ orgData.getOrgId() +", class："+ orgData.getClass() +"]");
        OrgData parent = orgRepo.findById(orgEntity.exsistByParentId());
        if (null == parent) throw InvalidException.DATA_NOT_EXSIST("父节点不存在");
        orgRepo.insertOne(orgEntity.addChild(parent.getOrgId(), parent.getOrgPath()));
    }

    public void updateOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        orgEntity.data().setOrgPath(null);//不可修改属性
        orgEntity.data().setParentId(null);//不可修改属性
        orgRepo.updateById(orgEntity.updateById());
    }

    @Transactional
    public void deleteOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        orgService.deleteOrgWithChildren(orgEntity, orgRepo);
    }

}
