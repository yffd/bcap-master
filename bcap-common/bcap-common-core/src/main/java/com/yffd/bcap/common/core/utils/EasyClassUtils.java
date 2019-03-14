package com.yffd.bcap.common.core.utils;

/**
 * 函数工具类：类.
 */
public class EasyClassUtils {

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private EasyClassUtils() {
        
    }

    /**
     * 获取类加载器.
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (null == cl) {
            // No thread context class loader -> use class loader of this class.
            cl = EasyClassUtils.class.getClassLoader();
            if (null == cl) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with
                    // null...
                }
            }
        }
        return cl;
    }
    
}

