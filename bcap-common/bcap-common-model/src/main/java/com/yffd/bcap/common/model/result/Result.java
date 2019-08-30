package com.yffd.bcap.common.model.result;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 1781348872239467770L;
    private Integer code;
    private String tip;
    private Object data;

    public Result() {
    }

    public Result(ResultCode resultCode, Object data) {
        this.data = data;
        this.code = resultCode.code();
        this.tip = resultCode.tip();
    }

    /** 返回成功 */
    public static Result ok() {
        return new Result(ResultCode.SUCCESS, null);
    }

    /** 返回成功 */
    public static Result ok(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    /** 返回失败 */
    public static Result fail(ResultCode resultCode) {
        return new Result(resultCode, null);
    }

    /** 返回失败 */
    public static Result fail(ResultCode resultCode, Object data) {
        return new Result(resultCode, data);
    }

    /** 返回失败 */
    public static Result fail(String tip) {
        Result result = new Result();
        result.setCode(ResultCode.FAIL.code());
        result.setTip(tip);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
