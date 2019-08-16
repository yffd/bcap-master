package com.yffd.bcap.common.model.result;

import java.io.Serializable;

public class DataResult implements Serializable {
    private String code;
    private String tip;
    private Object data;

    public DataResult() {
    }

    public DataResult(String code, String tip, Object data) {
        this.code = code;
        this.tip = tip;
        this.data = data;
    }

    public static DataResult ok() {
        return new DataResult(StatusResultEnum.OK.getCode(), StatusResultEnum.OK.getMsg(), null);
    }

    public static DataResult ok(String msg) {
        return new DataResult(StatusResultEnum.OK.getCode(), msg, null);
    }

    public static DataResult ok(String msg, Object data) {
        return new DataResult(StatusResultEnum.OK.getCode(), msg, data);
    }

    public static DataResult ok(String code, String msg, Object data) {
        return new DataResult(code, msg, data);
    }

    public static DataResult fail() {
        return new DataResult(StatusResultEnum.FAIL.getCode(), StatusResultEnum.FAIL.getMsg(), null);
    }

    public static DataResult fail(String msg) {
        return new DataResult(StatusResultEnum.FAIL.getCode(), msg, null);
    }

    public static DataResult fail(String msg, Object data) {
        return new DataResult(StatusResultEnum.FAIL.getCode(), msg, data);
    }

    public static DataResult fail(String code, String msg, Object data) {
        return new DataResult(code, msg, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
