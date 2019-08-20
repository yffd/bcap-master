package com.yffd.bcap.common.web.mvc.controller;

import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.web.constants.WebConstants;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebController {

    protected SysOperator sysOperator() {
        SysOperator operator = new SysOperator();
        operator.setOperateTime(new Date());
        return operator;
    }

    protected PageInfo pageInfo(Integer pageNum, Integer pageSize) {
        if (null == pageNum) pageNum = 0;
        if (null == pageSize) pageNum = WebConstants.KEY_PAGE_SIZE;
        return new PageInfo(pageNum, pageSize);
    }



/*
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @InitBinder
    public void intDate(WebDataBinder dataBinder) {
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    @InitBinder("page")
    public void initPage(WebDataBinder binder){
        System.out.println(">>>>>InitBinder>>>>initPage");
        binder.setFieldDefaultPrefix("page.");
    }

    @InitBinder("condition")
    public void initCondition(WebDataBinder binder){
        System.out.println(">>>>InitBinder>>>>>criteria");
        binder.setFieldDefaultPrefix("condition.");
    }
*/

}
