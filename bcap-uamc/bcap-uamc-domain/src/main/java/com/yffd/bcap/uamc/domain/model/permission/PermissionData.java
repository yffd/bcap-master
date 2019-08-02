package com.yffd.bcap.uamc.domain.model.permission;

import com.yffd.bcap.common.ddd.domain.data.DataObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_permission")
public class PermissionData extends DataObject {
    private static final long serialVersionUID = -8198274109263931920L;
    @Id
    private String pmsId;//权限ID
    private String pmsName;//权限名称
    private String pmsState;//状态
    private String pmsSourceType;//权限类型，菜单=menu、操作=operation、...
    private String pmsSourceId;//权限来源ID，指资源的ID
    private String remark;//描述

    public String getPmsId() {
        return pmsId;
    }

    public void setPmsId(String pmsId) {
        this.pmsId = pmsId;
    }

    public String getPmsName() {
        return pmsName;
    }

    public void setPmsName(String pmsName) {
        this.pmsName = pmsName;
    }

    public String getPmsState() {
        return pmsState;
    }

    public void setPmsState(String pmsState) {
        this.pmsState = pmsState;
    }

    public String getPmsSourceType() {
        return pmsSourceType;
    }

    public void setPmsSourceType(String pmsSourceType) {
        this.pmsSourceType = pmsSourceType;
    }

    public String getPmsSourceId() {
        return pmsSourceId;
    }

    public void setPmsSourceId(String pmsSourceId) {
        this.pmsSourceId = pmsSourceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
