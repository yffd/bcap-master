package com.yffd.bcap.common.model.result;

import java.text.MessageFormat;

public enum StatusResultEnum {
    /** ----------------- 公用错误代码(6000-6999)--------------------------- */
    OK("ok", "成功"),
    FAIL("fail", "失败"),
    PARAMS_INVALID("6002", "参数[{0}]无效"),


    OPERATION_EXPIRE("6003", "操作过期"),

    UPLOAD_FILE_ERROR("6008", "文件上传失败[{0}]"),

    ;

    String code; // 代码
    String msg; // 消息

    StatusResultEnum(String code, String msg) {
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
        for (StatusResultEnum c : StatusResultEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.msg;
            }
        }
        return null;
    }

    public static String getCodeByMsg(String msg) {
        for (StatusResultEnum c : StatusResultEnum.values()) {
            if (c.getMsg().equals(msg)) {
                return c.code;
            }
        }
        return null;
    }
}
