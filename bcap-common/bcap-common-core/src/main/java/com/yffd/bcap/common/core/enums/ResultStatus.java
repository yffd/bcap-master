package com.yffd.bcap.common.core.enums;

public enum ResultStatus {
    STATUS_OK("1", "成功"),
    STATUS_FAIL("0", "失败"),
    STATUS_404("404", "未找到有效资源"),
    STATUS_500("500", "系统错误"),
    STATUS_PROJECTED("1000", "熔断保护错误"),
    ;

    private String code;
    private String desc;

    ResultStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
