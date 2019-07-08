package com.yffd.bcap.uamc.domain.constants.enums;

public enum ActiveStateEnum {
    ACTIVE("1", "启用"), INACTIVE("0", "禁用")
    ;

    private String code;
    private String desc;

    ActiveStateEnum(String code, String desc) {
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
