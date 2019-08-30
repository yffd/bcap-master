package com.yffd.bcap.uamc.application.organization.dto;

import com.yffd.bcap.common.model.tree.Treeable;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;

import java.beans.Transient;
import java.util.List;

public class OrganizationNode extends OrgData implements Treeable<OrganizationNode>  {
    private List<OrganizationNode> children;
    private Integer userNum;//机构人数

    public Integer getUserNum() {
        return userNum;
    }

    @Transient
    @Override
    public String getOrgPath() {
        return super.getOrgPath();
    }
    @Transient
    @Override
    public Object getIdValue() {
        return this.getOrgId();
    }
    @Transient
    @Override
    public Object getPidValue() {
        return this.getParentId();
    }

    @Override
    public List<OrganizationNode> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<OrganizationNode> children) {
        this.children = children;
    }
}
