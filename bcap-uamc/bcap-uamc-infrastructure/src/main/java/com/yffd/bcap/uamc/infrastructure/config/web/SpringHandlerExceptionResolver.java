package com.yffd.bcap.uamc.infrastructure.config.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.yffd.bcap.common.model.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SpringHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {
    private static Logger logger = LoggerFactory.getLogger(SpringHandlerExceptionResolver.class);
    private int order = Ordered.HIGHEST_PRECEDENCE;
    private FastJsonConfig fastJsonConfig;

    public SpringHandlerExceptionResolver(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = specialExceptionResolve(ex, request);
        if (null == mv) {
            String message = "系统异常，请联系管理员";
            if (ex instanceof BaseException) {
                message = ex.getMessage();
            }
            mv = errorResult(message, "/error", request);
        }
        return mv;
    }

    @Override
    public int getOrder() {
        return order;
    }

    /**
     * 返回错误信息
     * @param message   错误信息
     * @param url       错误页url
     * @param request
     * @return
     */
    private ModelAndView errorResult(String message, String url, HttpServletRequest request) {
        logger.warn("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(), message);
        if (isAjax(request)) {
            return ajaxResult("500", message);
        } else {
            return normalResult(message, url);
        }
    }

    /**
     * {@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver#doResolveException}
     * @param ex
     * @param request
     * @return
     */
    private ModelAndView specialExceptionResolve(Exception ex, HttpServletRequest request) {
        try {
            if (ex instanceof BaseException) {
                return result((BaseException) ex, request);
            }
        } catch (Exception handlerException) {
            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }
        return null;
    }

    /**
     * 返回异常信息
     * @param httpException
     * @param request
     * @return
     */
    private ModelAndView result(BaseException httpException, HttpServletRequest request) {
        logger.warn("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(), httpException.getMessage());
        if (isAjax(request)) {
            return ajaxResult(httpException.getCode(), httpException.getMessage());
        } else {
            return normalResult(httpException.getMessage(), "/error");
        }
    }

    /**
     * 返回错误页
     * @param message   错误信息
     * @param url       错误页url
     * @return
     */
    private ModelAndView normalResult(String message, String url) {
        Map<String, String> model = new HashMap<String, String>();
        model.put("errorMessage", message);
        return new ModelAndView(url, model);
    }

    /**
     * 返回错误数据
     * @param code      错误码
     * @param message   错误信息
     * @return
     */
    private ModelAndView ajaxResult(String code, String message) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        view.setFastJsonConfig(fastJsonConfig);
        view.setAttributesMap((JSONObject) JSON.toJSON(JsonResult.fail(code, message)));
        mv.setView(view);
        return mv;
    }

    /**
     * 判断是否ajax请求
     * @param request
     * @return      true:ajax请求  false:非ajax请求
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
}
