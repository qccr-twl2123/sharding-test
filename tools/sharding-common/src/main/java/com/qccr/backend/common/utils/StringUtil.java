/**
 * qccr.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.qccr.backend.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

/**
 * 字符串通用处理类
 * @author zhouzhizhang
 * @version $Id: StringUtil.java, v 0.1 2015年11月18日 上午11:21:48 zhouzhizhang Exp $
 */
public class StringUtil {

    /**  
     * 将字符串转换为Integer
     * @param source 来源字符串
     * @return Integer
     * @date: 2015年11月18日 上午11:22:43
     */
    public static Integer parseInteger(String source) {
        //对字符串进行前置检查 - 空值判断
        if (source == null) {
            return null;
        }
        String trimedSource = source.trim();
        if (trimedSource.length() == 0) {
            return null;
        }
        return Integer.parseInt(trimedSource);
    }

    /**  
     * 对象equal对比
     * @param source 来源对象
     * @param target 目标对象
     * @return true：两者相等， false:两者不相等
     */
    public static boolean equal(Object source, Object target) {
        //两者为同一个对象，或者同是都为null, 视为相同
        if (source == target) {
            return true;
        }
        //两者其中之一为null, 视为不同
        if (source == null || target == null) {
            return false;
        }
        //调用对象内置的对比方法
        return source.equals(target);
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> converterStringArrayToOtherArray(List<String> source, E e) {
        List<E> result = Lists.newArrayList();
        if (null == source) {
            return null;
        }
        String c = e.getClass().getCanonicalName();
        Method method = null;
        for (String s : source) {
            try {
                switch (c) {
                    case "java.lang.Integer":
                        method = e.getClass().getMethod("parseInt", s.getClass());
                        break;
                    case "java.lang.Float":
                        method = e.getClass().getMethod("parseFloat", s.getClass());
                        break;
                    case "java.lang.Double":
                        method = e.getClass().getMethod("parseDouble", s.getClass());
                        break;
                    case "java.lang.Long":
                        method = e.getClass().getMethod("parseLong", s.getClass());
                        break;
                    default:
                        break;
                }
                if (null != method) {
                    result.add((E) (method.invoke(new Class[] { e.getClass() }, s)));
                }
            } catch (Exception e1) {
                return null;
            }
        }
        return result;
    }

    public static Pattern ILLEGAL_PATTERN = Pattern.compile("[()‘|'<>（）]");

    /**
     * 判断手机号码是否有效 增加了14x,17x的手机号码判断
     * 
     * @param num
     * @return true 是正确的手机号码;
     */
    public static boolean isMobileNum(String num) {
        if (isNullOrEmpty(num))
            return false;
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /**
     * 判断手机号码是否有效 增加了14x,17x的手机号码判断
     * 
     * @param num
     * @return true 是正确的手机号码;
     */
    public static String IllegalCharacter(String params) {
        Matcher m = ILLEGAL_PATTERN.matcher(params);
        return m.replaceAll("").trim();
    }

    /**
     * 参数检测，判断是否为 null 或者 ""
     * 
     * @param needCheck
     *            : 待检测字符串
     * @return true: 输入字符串为 null 或者trim后为""<br>
     *         false：输入字符串 不为null 并且 trim后不等于 ""
     */
    public final static boolean isNullOrEmpty(final String needCheck) {
        return needCheck == null || needCheck.trim().isEmpty();
    }

    /**
     * 参数检测，判断是否为 null 或者 "" 或者由空白组成
     * 
     * @param needCheck
     *            : 待检测字符串(一个或多个)
     * @return true: 输入字符串任一个 为 null 或者 ""<br>
     *         ———— needCheck==null || needCheck.trim().equals("")<br>
     *         false：输入字符串 全部 不为null不等于 ""
     */
    public static boolean isNullOrEmpty(final String str1, final String str2) {
        return str1 == null || str1.trim().isEmpty() || str2 == null || str2.trim().isEmpty();
    }

    /**
     * 参数检测，判断是否为 null 或者 "" 或者由空白组成
     * 
     * @param needCheck
     *            : 待检测字符串(一个或多个)
     * @return true: 输入字符串任一个 为 null 或者 ""<br>
     *         ———— needCheck==null || needCheck.trim().equals("")<br>
     *         false：输入字符串 全部 不为null不等于 ""
     */
    public static boolean isNullOrEmpty(final String str1, final String str2, final String str3) {
        return str1 == null || str1.trim().isEmpty() || str2 == null || str2.trim().isEmpty()
               || str3 == null || str3.trim().isEmpty();
    }

    /**
     * 参数检测，判断是否为 null 或者 "" 或者由空白组成
     * 
     * @param needCheck
     *            : 待检测字符串(一个或多个)
     * @return true: 输入字符串任一个 为 null 或者 ""<br>
     *         ———— needCheck==null || needCheck.trim().equals("")<br>
     *         false：输入字符串 全部 不为null不等于 ""
     */
    public static boolean isNullOrEmpty(final String str1, final String str2, final String str3,
                                        final String str4) {
        return str1 == null || str1.trim().isEmpty() || str2 == null || str2.trim().isEmpty()
               || str3 == null || str3.trim().isEmpty() || str4 == null || str4.trim().isEmpty();
    }

    /**
     * 参数检测，判断是否为 null 或者 "" 或者由空白组成
     * 
     * @param needCheck
     *            : 待检测字符串(一个或多个)
     * @return true: 输入字符串任一个 为 null 或者 ""<br>
     *         ———— needCheck==null || needCheck.trim().equals("")<br>
     *         false：输入字符串 全部 不为null不等于 ""
     */
    public static boolean isNullOrEmpty(String... needCheck) {
        for (String curr : needCheck) {
            if (curr == null || curr.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是数字，含null与""的验证
     * 
     * @return true: 数字 false：含有其他字符
     */
    public static boolean isNumeric(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getSerialNo() {
        return UUID.randomUUID().toString();
    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key).toString();

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createCacheLinkString(Map<String, Object> params) {

        Iterator<Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            Object key = entry.getValue();
            if (Integer.valueOf("0").equals(key) || key == null) {
                it.remove(); //OK 
            }
        }
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key).toString();

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "_" + value;
            } else {
                prestr = prestr + key + "_" + value + "_";
            }
        }

        return prestr;
    }

}
