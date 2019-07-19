package com.yffd.bcap.common.model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BcapStringUtils {
    /** 换行符 */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 检查一个字符串为空
     * <pre>
     * BcapStringUtils.isEmpty(null)      = true
     * BcapStringUtils.isEmpty("")        = true
     * BcapStringUtils.isEmpty(" ")       = true
     * BcapStringUtils.isEmpty("bob")     = false
     * BcapStringUtils.isEmpty("  bob  ") = false
     * </pre>
     * @param str
     * @return
     */
    public static boolean isEmpty(final String str) {
        return (null==str || "".equals(str.trim()));
    }

    /**
     * 检查一个字符不为空
     * <pre>
     * BcapStringUtils.isEmpty(null)      = false
     * BcapStringUtils.isEmpty("")        = false
     * BcapStringUtils.isEmpty(" ")       = false
     * BcapStringUtils.isEmpty("bob")     = true
     * BcapStringUtils.isEmpty("  bob  ") = true
     * </pre>
     * @param str
     * @return
     */
    public static boolean isNotEmpty(final String str) {
        return !isEmpty(str);
    }

    /**
     * 获取换行符. <br/>
     * @return
     */
    public static String getLineSeparator() {
        return LINE_SEPARATOR;
    }

    /**
     * 驼峰格式转换为下划线格式
     * @param str				下划线源字符串
     * @param upperCase			转换后的字符串是否大写返回，true：大写返回，false：小写返回
     * @return
     */
    public static String camel2underline(String str, boolean upperCase) {
        return camel2underline(str, upperCase, null, null);
    }

    /**
     * 驼峰格式转换为下划线格式
     * @param str				下划线源字符串
     * @param upperCase			转换后的字符串是否大写返回，true：大写返回，false：小写返回
     * @param prefix			前缀字符串
     * @param suffix			后缀字符串
     * @return
     */
    public static String camel2underline(String str, boolean upperCase, String prefix, String suffix) {
        if(str==null || "".equals(str.trim())) {
            return "";
        }
        if(str.indexOf("_")>-1) {
            if(upperCase) {
                return str.toUpperCase();
            } else {
                return str;
            }
        }
        str = String.valueOf(str.charAt(0)).toUpperCase().concat(str.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)+");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end()==str.length()?"":"_");
        }
        if(null!=suffix && !"".equals(suffix)) {
            sb.append(suffix);
        }
        if(null!=prefix && !"".equals(prefix)) {
            return prefix + sb.toString();
        }
        if(upperCase) {
            return sb.toString().toUpperCase();
        } else {
            return sb.toString().toLowerCase();
        }
    }

    /**
     * 下划线格式转驼峰格式
     * @param str			驼峰源字符串
     * @param smallCamel	大小驼峰，true：小驼峰，false：大驼峰
     * @return
     */
    public static String underline2camel(String str, boolean smallCamel) {
        return underline2camel(str, smallCamel, null, null);
    }

    /**
     * 下划线格式转驼峰格式
     * @param str			驼峰源字符串
     * @param smallCamel	大小驼峰，true：小驼峰，false：大驼峰
     * @param prefix		前缀字符串
     * @param suffix		后缀字符串
     * @return
     */
    public static String underline2camel(String str, boolean smallCamel, String prefix, String suffix) {
        if(str==null || "".equals(str.trim())) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        if(str.indexOf("_")==-1) {
            Pattern pattern = Pattern.compile("([A-Z]+)?");
            Matcher matcher = pattern.matcher(str);
            if(matcher.matches()) {
                sb.append(smallCamel?Character.toLowerCase(str.charAt(0)):Character.toUpperCase(str.charAt(0)));
                sb.append(str.substring(1, str.length()).toLowerCase());
            } else if(matcher.find()) {
                sb.append(smallCamel?Character.toLowerCase(str.charAt(0)):Character.toUpperCase(str.charAt(0)));
                sb.append(str.substring(1, str.length()));
            } else {
                sb.append(smallCamel?Character.toLowerCase(str.charAt(0)):Character.toUpperCase(str.charAt(0)));
                sb.append(str.substring(1, str.length()).toLowerCase());
            }
        } else {
            Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
            Matcher matcher = pattern.matcher(str);
            while(matcher.find()) {
                String word = matcher.group();
                sb.append(smallCamel && matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
                int index = word.lastIndexOf('_');
                if(index>0) {
                    sb.append(word.substring(1, index).toLowerCase());
                } else {
                    sb.append(word.substring(1).toLowerCase());
                }
            }
        }
        if(null!=suffix && !"".equals(suffix)) {
            sb.append(suffix);
        }
        if(null!=prefix && !"".equals(prefix)) {
            return prefix + sb.toString();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String prefix = "";
        String suffix = "";
        System.out.println(camel2underline("username", false, prefix, suffix));
//        System.out.println(camel2underline("username", true, prefix, suffix));
//        System.out.println(camel2underline("userName", true, prefix, suffix));
//        System.out.println(camel2underline("userName", false, prefix, suffix));
//        System.out.println(camel2underline("user_name", true, prefix, suffix));
//        System.out.println(camel2underline("USER_NAME", true, prefix, suffix));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");

        System.out.println(underline2camel("userName", true, prefix, suffix));
//        System.out.println(underline2camel("userName", false, prefix, suffix));
//        System.out.println(underline2camel("USER_NAME_PWD", true, prefix, suffix));
//        System.out.println(underline2camel("USER_NAME_PWD", false, prefix, suffix));
//        System.out.println(underline2camel("USER_NAME_", true, prefix, suffix));
//        System.out.println(underline2camel("USER_NAME_", false, prefix, suffix));
//        System.out.println(underline2camel("user_name_", true, prefix, suffix));
//        System.out.println(underline2camel("user_name_", false, prefix, suffix));
        System.out.println(underline2camel("AGE", true, prefix, suffix));
    }

}


