package com.yffd.bcap.uamc.application.organization.service;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.application.organization.assembler.OrganizationAssembler;
import com.yffd.bcap.uamc.application.organization.dto.OrganizationTree;
import com.yffd.bcap.uamc.application.organization.query.OrganizationQry;
import com.yffd.bcap.uamc.domain.exception.UamcDomainValidateException;
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

    public OrganizationTree buildTree() {
        List<OrgData> dataList = organizationQry.findAll();
        return OrganizationAssembler.buildTree(dataList);
    }

    public void addOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        if (orgService.exsistOrgById(orgEntity, orgRepo))
            throw UamcDomainValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ orgData.getOrgId() +", class："+ orgData.getClass() +"]");
        orgRepo.insertOne(orgEntity.add());
    }

    public void updateOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        orgRepo.updateById(orgEntity.updateById());
    }

    @Transactional
    public void deleteOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        orgService.deleteOrgWithChildren(orgEntity, orgRepo);
    }

}
