package com.yffd.bcap.common.support.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanUtil {
    // 私有化构造方法，将工具类设置为单例模式。
    private JavaBeanUtil() {}

    public static Map<String, Class<?>> getProps(Class<?> javabeanClazz) {
        Map<String, Class<?>> retMap = new HashMap<>();
        if (null == javabeanClazz) return retMap;

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(javabeanClazz);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            if (null == pds) return retMap;
            for (PropertyDescriptor pd : pds) {
                String propName = pd.getName();
                Class<?> propType = pd.getPropertyType();
                retMap.put(propName, propType);
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException("反射java bean错误", e);
        }

        return retMap;
    }

    public static Map<String, Class<?>> getProps(Class<?> javabeanClazz, Class<?> stopClazz) {
        Map<String, Class<?>> retMap = new HashMap<>();
        if (null == javabeanClazz) return retMap;

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(javabeanClazz, stopClazz);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            if (null == pds) return retMap;
            for (PropertyDescriptor pd : pds) {
                String propName = pd.getName();
                Class<?> propType = pd.getPropertyType();
                retMap.put(propName, propType);
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException("反射java bean错误", e);
        }

        return retMap;
    }

    /**
     * json字符串转bean对象
     * @param json
     * @param beanClazz
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> beanClazz) {
//        return JSON.parseObject(json, beanClazz);
        return JSON.parseObject(json, new TypeReference<T>(){});
    }

    /**
     * json字符串转bean对象
     * @param json
     * @param beanClass
     * @return
     */
    public static <T> List<T> json2BeanList(String json, Class beanClass) {
        List<T> list = null;
        if (json != null && !"".equals(json)) {
            try {
                list = JSONArray.parseArray(json, beanClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * bean对象转json字符串
     * @param bean
     * @return
     */
    public static String bean2Json(Object bean) {
        return JSON.toJSONString(bean);
    }

}
