package com.yffd.bcap.common.support.constants;

import java.text.MessageFormat;

public enum ResultStatus {
    // ----------------- 公用错误代码(1000-1999)---------------------------
    SUCCESS("1000", "成功"),
    FAILED("1001", "失败"),
    PARAMS_INVALID("1002", "参数[{0}]无效"),


    OPERATION_EXPIRE("1003", "操作过期"),

    UPLOAD_FILE_ERROR("1008", "文件上传失败[{0}]"),

    ;

    String code; // 代码
    String msg; // 消息

    ResultStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFullMsg(String... arg) {
        if (null == arg || arg.length == 0) return this.msg;
        else return MessageFormat.format(this.msg, arg);
    }

    public static String getMsgByCode(String code) {
        for (ResultStatus c : ResultStatus.values()) {
            if (c.getCode().equals(code)) {
                return c.msg;
            }
        }
        return null;
    }

    public static String getCodeByMsg(String msg) {
        for (ResultStatus c : ResultStatus.values()) {
            if (c.getMsg().equals(msg)) {
                return c.code;
            }
        }
        return null;
    }
}
