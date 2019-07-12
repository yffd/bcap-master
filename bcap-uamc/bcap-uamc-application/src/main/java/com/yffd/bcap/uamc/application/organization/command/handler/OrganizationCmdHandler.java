package com.yffd.bcap.uamc.application.organization.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.organization.OrgEntity;
import com.yffd.bcap.uamc.domain.model.organization.OrgRepo;
import org.springframework.transaction.annotation.Transactional;

public class OrganizationCmdHandler {
    private OrgRepo orgRepo;

    @Transactional
    public void deleteRole(OrgData orgData, SysOperator sysOperator) {
        OrgEntity orgEntity = new OrgEntity(orgData, sysOperator);
        orgRepo.deleteById(orgEntity.deleteById());
        //1.若有子机构，则子机构一起删除；
        //TODO
        //2.若该机构、以及该机构子机构下拥有用户，则不能删除机构；
    }
}
