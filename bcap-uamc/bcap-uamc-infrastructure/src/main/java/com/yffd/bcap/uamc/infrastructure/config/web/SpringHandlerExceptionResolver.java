package com.yffd.bcap.uamc.infrastructure.config.web;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.yffd.bcap.common.model.exception.SysException;
import com.yffd.bcap.common.model.exception.SysExceptionEnum;
import com.yffd.bcap.common.model.result.StatusResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SpringHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {
    private static Logger logger = LoggerFactory.getLogger(SpringHandlerExceptionResolver.class);
    private int order = Ordered.HIGHEST_PRECEDENCE;
    private FastJsonConfig fastJsonConfig;
    private static final String ERROR_PATH = "/error";
    private static final String STATUS_FAIL = StatusResultEnum.FAIL.getCode();
    private static final String STATUS_TIP = "系统异常[%s]";

    public SpringHandlerExceptionResolver(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof SysException) {
            logger.warn("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(), ex.getMessage());
        } else {
            logger.warn("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(), "系统未知异常");
        }
        if (isAjax(request)) {
            return ajaxResult(ex);
        } else {
          return normalResult(ex, ERROR_PATH);
        }
    }

    @Override
    public int getOrder() {
        return order;
    }

    /**
     * 判断是否ajax请求
     * @param request
     * @return      true:ajax请求  false:非ajax请求
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 返回错误页
     * @param ex
     * @param url       错误页url
     * @return
     */
    private ModelAndView normalResult(Exception ex, String url) {
        return new ModelAndView(url, resolveException(ex));
    }

    /**
     * 返回错误数据
     * @param ex
     * @return
     */
    private ModelAndView ajaxResult(Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        view.setFastJsonConfig(fastJsonConfig);
        view.setAttributesMap(resolveException(ex));
        mv.setView(view);
        return mv;
    }

    private Map<String, Object> resolveException(Exception ex) {
        if (ex instanceof SysException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, ((SysException) ex).getCode()));
        } else if (ex instanceof NoHandlerFoundException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_NOT_FOUND.getCode())));
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_NOT_SUPPORTED_METHOD.getCode())));
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_NOT_SUPPORTED_MEDIA_TYPE.getCode())));
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_NOT_ACCEPTABLE_MEDIA_TYPE.getCode())));
        } else if (ex instanceof MissingPathVariableException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_NOT_SUPPORTED_METHOD.getCode())));
        } else if (ex instanceof MissingServletRequestParameterException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_MISSING_REQUEST_PARAMETER.getCode())));
        } else if (ex instanceof ConversionNotSupportedException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_NOT_SUPPORTED_CONVERSION.getCode())));
        } else if (ex instanceof HttpMessageNotReadableException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_MESSAGE_NOT_READABLE.getCode())));
        } else if (ex instanceof HttpMessageNotWritableException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_MESSAGE_NOT_WRITABLE.getCode())));
        } else if (ex instanceof MethodArgumentNotValidException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_NOT_VALID_METHOD_ARGUMENT.getCode())));
        } else if (ex instanceof MissingServletRequestPartException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_MISSING_REQUEST_PART.getCode())));
        } else if (ex instanceof AsyncRequestTimeoutException) {
            return buildModel(STATUS_FAIL, String.format(STATUS_TIP, (SysExceptionEnum.API_ASYNC_REQUEST_TIMEOUT.getCode())));
        }
        return null;
    }

    private Map<String, Object> buildModel(String code, String tip) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("tip", tip);
        return map;
    }

}
