package com.yffd.bcap.common.core.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = -506720388203330665L;

    private static String CODE_OK = "ok";
    private static String CODE_ERROR = "error";

    private String code;    //状态码
    private String msg;     //消息提示
    private T data;         //内容数据

    private ResultData() {
    }

    public static <T> ResultData<T> OK() {
        ResultData<T> result =  new ResultData();
        result.setCode(CODE_OK);
        result.setMsg("成功");
        return result;
    }

    public static <T> ResultData<T> OK(T data) {
        ResultData result =  new ResultData();
        result.setCode(CODE_OK);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> OK(String msg) {
        ResultData<T> result =  new ResultData();
        result.setCode(CODE_OK);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultData<T> ERROR(String msg) {
        ResultData<T> result =  new ResultData();
        result.setCode(CODE_ERROR);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultData<T> ERROR_404() {
        ResultData<T> result =  new ResultData();
        result.setCode(CODE_ERROR);
        result.setMsg("未找到资源");
        return result;
    }

    public static <T> ResultData<T> ERROR_500() {
        ResultData<T> result =  new ResultData();
        result.setCode(CODE_ERROR);
        result.setMsg("系统内部错误");
        return result;
    }

    public static <T> ResultData<T> ERROR_PROTECTED() {
        ResultData<T> result =  new ResultData();
        result.setCode(CODE_ERROR);
        result.setMsg("系统熔断保护");
        return result;
    }

}
