package com.yffd.bcap.uamc.infrastructure.log;

import java.io.Serializable;

public class WebAccessMsg implements Serializable {
    private static final long serialVersionUID = -3585847963864104906L;
    //客户端请求IP
    private String clientip;
    //请求路径
    private String reqUri;
    //请求类型（json | 普通类型）
    private String reqType;
    //请求方式：post、get等
    private String reqMethod;
    //请求参数
    private String paramData;
    //session标识
    private String sessionid;
    //请求时间
    private Long reqTime;
    //响应时间
    private Long respTime;
    //时间差
    private Long intervalTime;
    //响应错误码
    private int respStatus;
    //响应数据内容
    private String respData;

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getReqUri() {
        return reqUri;
    }

    public void setReqUri(String reqUri) {
        this.reqUri = reqUri;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Long getReqTime() {
        return reqTime;
    }

    public void setReqTime(Long reqTime) {
        this.reqTime = reqTime;
    }

    public Long getRespTime() {
        return respTime;
    }

    public void setRespTime(Long respTime) {
        this.respTime = respTime;
    }

    public Long getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getRespStatus() {
        return respStatus;
    }

    public void setRespStatus(int respStatus) {
        this.respStatus = respStatus;
    }

    public String getRespData() {
        return respData;
    }

    public void setRespData(String respData) {
        this.respData = respData;
    }
}
