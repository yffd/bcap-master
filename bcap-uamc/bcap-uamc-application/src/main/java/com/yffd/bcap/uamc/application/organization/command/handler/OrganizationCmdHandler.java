package com.yffd.bcap.uamc.application.organization.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.organization.OrgEntity;
import com.yffd.bcap.uamc.domain.model.organization.OrgRepo;
import com.yffd.bcap.uamc.domain.model.organization.OrgService;
import org.springframework.transaction.annotation.Transactional;

public class OrganizationCmdHandler {
    private OrgRepo orgRepo;

    public void addOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        if (exsistById(orgEntity))
            throw BcapValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ orgData.getOrgId() +", class："+ orgData.getClass() +"]");
        orgRepo.insertOne(orgEntity.add());
    }

    public void updateOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        orgRepo.updateById(orgEntity.updateById());
    }

    @Transactional
    public void deleteOrg(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        OrgService.instance().deleteOrgWithChildren(orgEntity, orgRepo);
    }

    private Boolean exsistById(OrgEntity orgEntity) {
        return null != orgRepo.findById(orgEntity.exsistById());
    }

}
