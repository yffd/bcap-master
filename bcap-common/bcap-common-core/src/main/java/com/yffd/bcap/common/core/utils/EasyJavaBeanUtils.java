package com.yffd.bcap.common.core.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

public class EasyJavaBeanUtils {

	// 私有化构造方法，将工具类设置为单例模式。
	private EasyJavaBeanUtils() {}
	
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
	
}

