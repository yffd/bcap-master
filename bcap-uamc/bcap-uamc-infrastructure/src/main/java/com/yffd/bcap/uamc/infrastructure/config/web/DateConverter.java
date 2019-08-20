package com.yffd.bcap.uamc.infrastructure.config.web;

import com.yffd.bcap.common.model.utils.BcapDateUtils;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 日期格式转换器，将任意格式的字符串形式的日期转换成java.util.Date类型
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if (BcapStringUtils.isEmpty(source)) return null;
        return BcapDateUtils.parseToDate(source);
    }

}

