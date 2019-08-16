package com.yffd.bcap.uamc.infrastructure.config.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yffd.bcap.common.web.constants.WebConstants;
import com.yffd.bcap.common.web.util.HttpServletUtils;
import com.yffd.bcap.common.web.log.WebAccessLogRecorder;
import com.yffd.bcap.common.web.log.WebAccessMsg;
import com.yffd.bcap.common.web.log.WebAccessRecorder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor implements HandlerInterceptor {
    private static final String WEB_ACCESS_RECORD = "_web_access_record";
    private static final WebAccessRecorder recorder = new WebAccessLogRecorder();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(WEB_ACCESS_RECORD, buildMsg(request));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long currentTime = System.currentTimeMillis();
        WebAccessMsg webAccessMsg = (WebAccessMsg) request.getAttribute(WEB_ACCESS_RECORD);
        webAccessMsg.setIntervalTime(currentTime - webAccessMsg.getReqTime());
        webAccessMsg.setRespTime(currentTime);
        webAccessMsg.setRespStatus(response.getStatus());
        String respData = JSON.toJSONString(WebConstants.KEY_WEB_RESP_DATA,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        webAccessMsg.setRespData(respData);
        // 记录访问信息
        recorder.recorde(webAccessMsg);
    }

    private WebAccessMsg buildMsg(HttpServletRequest request) {
        //请求参数
        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        WebAccessMsg webAccessMsg = new WebAccessMsg();
        webAccessMsg.setSessionid(request.getRequestedSessionId());
        webAccessMsg.setClientip(HttpServletUtils.getRealIpAddr(request));//客户端IP
        webAccessMsg.setReqUri(request.getRequestURI());//请求路径
        webAccessMsg.setReqType(HttpServletUtils.getReqType(request));//请求类型（异步请求 | 普通请求）
        webAccessMsg.setReqMethod(request.getMethod());//请求方法
        webAccessMsg.setParamData(paramData);
        webAccessMsg.setSessionid(request.getRequestedSessionId());//session标识
        webAccessMsg.setReqTime(System.currentTimeMillis());
        return webAccessMsg;
    }
}
