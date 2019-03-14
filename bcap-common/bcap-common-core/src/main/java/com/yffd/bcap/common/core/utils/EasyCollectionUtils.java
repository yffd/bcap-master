package com.yffd.bcap.common.core.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class EasyCollectionUtils {

	/**
	 * 检查一个List容器中的数据项为空
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		return null == list ? true : list.size() == 0;
	}

	/**
	 * 检查一个List容器中的数据项不为空
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	/**
	 * 检查一个Set容器中的数据项为空
	 * @param set
	 * @return
	 */
	public static boolean isEmpty(Set<?> set) {
		return null == set ? true : set.size() == 0;
	}

	/**
	 * 检查一个Set容器中的数据项不为空
	 * @param set
	 * @return
	 */
	public static boolean isNotEmpty(Set<?> set) {
		return !isEmpty(set);
	}

	/**
	 * 检查一个Map容器中的数据项为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return null == map ? true : map.size() == 0;
	}

	/**
	 * 检查一个Map容器中的数据项不为空
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * 检查一个Array容器中的数据项为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		return null == array ? true : array.length == 0;
	}

	/**
	 * 检查一个Array容器中的数据项不为空
	 * @param array
	 * @return
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}
	
	
}

