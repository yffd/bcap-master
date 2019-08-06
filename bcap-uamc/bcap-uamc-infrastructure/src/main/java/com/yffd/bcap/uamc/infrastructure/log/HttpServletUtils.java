package com.yffd.bcap.uamc.infrastructure.log;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtils {

    /**
     * 获取请求类型，是异步请求（async），还是传统请求（sync）
     * @param request
     * @return
     */
    public static String getReqType(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (null == requestType) return "sync";
        return "async";
    }

    /**
     * 获取用户访问的真实IP地址，不使用request.getRemoteAddr()的原因，是有可能用户使用了代理软件方式避免真实IP地址
     * @param request
     * @return
     */
    public static String getRealIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
