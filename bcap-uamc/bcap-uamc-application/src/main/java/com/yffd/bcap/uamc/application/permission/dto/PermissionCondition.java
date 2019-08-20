package com.yffd.bcap.uamc.application.permission.dto;

import com.yffd.bcap.common.ddd.domain.valobj.IValueObject;

public class PermissionCondition implements IValueObject {
    private static final long serialVersionUID = -4094949798260929241L;
    private String pmsId;//权限ID
    private String pmsName;//权限名称
    private String pmsState;//状态

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
}
