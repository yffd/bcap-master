package com.yffd.bcap.uamc.infrastructure.persistence.organization.jpa.query;

import com.yffd.bcap.uamc.application.organization.query.OrganizationQry;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.infrastructure.persistence.organization.jpa.JpaOrgRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaOrgQry implements OrganizationQry {
    @Autowired
    private JpaOrgRepoPlus jpaOrgRepoPlus;

    @Override
    public List<OrgData> findAll() {
        return jpaOrgRepoPlus.findAll();
    }

}