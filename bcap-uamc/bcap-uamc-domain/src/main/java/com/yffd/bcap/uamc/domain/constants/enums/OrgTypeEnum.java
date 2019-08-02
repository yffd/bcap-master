package com.yffd.bcap.uamc.domain.constants.enums;

public enum OrgTypeEnum {
    COMPANY("company", "企业"), BRANCH("branch", "分公司"),DEPT("dept", "部门")
    ;
    private String code;
    private String desc;

    OrgTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
