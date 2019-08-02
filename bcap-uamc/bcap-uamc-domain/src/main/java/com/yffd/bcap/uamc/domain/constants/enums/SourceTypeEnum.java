package com.yffd.bcap.uamc.domain.constants.enums;

public enum SourceTypeEnum {
    MENU("menu", "菜单"), OPREATION("oprt", "操作")
    ;

    private String code;
    private String desc;

    SourceTypeEnum(String code, String desc) {
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
