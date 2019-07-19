package com.yffd.bcap.uamc.domain.model.organization;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_org")
public class OrgData extends DataObjectSupport {
    private static final long serialVersionUID = 2689173809583888360L;
    @Id
    private String orgId;//机构ID
    private String orgName;//机构名称
    private String shotName;//机构简称
    private String parentId;//父机构ID
    private String orgType;//类型，企业=company、分公司=branch、部门=dept
    private String num;//排序号
    private String orgPath;//机构路径
    private String remark;//描述

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getShotName() {
        return shotName;
    }

    public void setShotName(String shotName) {
        this.shotName = shotName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
