package com.yffd.bcap.uamc.application.organization.query;

import com.yffd.bcap.uamc.domain.model.organization.OrgData;

import java.util.List;

public interface OrganizationQry {

    List<OrgData> findAll();
}
