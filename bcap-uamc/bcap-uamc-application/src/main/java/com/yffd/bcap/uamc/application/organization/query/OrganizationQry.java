package com.yffd.bcap.uamc.application.organization.query;

import com.yffd.bcap.uamc.application.organization.dto.OrganizationNode;

import java.util.List;

public interface OrganizationQry {

    List<OrganizationNode> findNodeList();
}
