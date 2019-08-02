package com.yffd.bcap.uamc.application.organization.dto;

import com.yffd.bcap.common.model.tree.Treeable;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;

import java.util.List;

public class OrganizationTree extends OrgData implements Treeable<OrganizationTree>  {
    private List<OrganizationTree> children;

    @Override
    public Object getIdValue() {
        return this.getOrgId();
    }

    @Override
    public Object getPidValue() {
        return this.getParentId();
    }

    @Override
    public List<OrganizationTree> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<OrganizationTree> children) {
        this.children = children;
    }
}
