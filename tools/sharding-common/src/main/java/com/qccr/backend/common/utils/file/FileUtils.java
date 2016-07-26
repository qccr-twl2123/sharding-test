/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.backend.common.utils.file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

/**
 * 
 * @author wangyz
 * @version $Id: FileUtil.java, v 0.1 2016年3月8日 下午9:31:15 wangyz Exp $
 */
public class FileUtils {

    /**
     * 文本转换成list
     * 要求必须对象属性和导入数据排序一致
     * @param inputStream
     * @param split
     * @param clazz
     * @return
     * @throws Exception
     * @date: 2016年3月8日 下午10:32:06
     */
    public static <E> List<E> parseArray(InputStream inputStream, String split,
                                         Class<E> clazz) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String row = null;
        List<E> result = Lists.newArrayList();
        while ((row = reader.readLine()) != null) {
            if (row.indexOf(split) < 0) {
                throw new IllegalAccessException("文件内容错误");
            }
            String[] rows = row.split(split);
            Field[] field = clazz.getDeclaredFields();
            E entity = BeanUtils.instantiate(clazz);
            for (int i = 0; i < rows.length; i++) {
                if (i > field.length - 1) {
                    break;
                }
                Method method = entity.getClass().getMethod("set" + getName(field[i].getName()),
                    field[i].getType());
                Object rowData = rows[i].trim();
                if (field[i].getType().equals(Integer.class)) {
                    rowData = Integer.parseInt(rowData.toString());
                }
                if (field[i].getType().equals(String.class)) {
                    rowData = rowData.toString();
                }
                if (field[i].getType().equals(Integer.class)) {
                    rowData = Integer.parseInt(rowData.toString());
                }
                method.invoke(entity, rowData);
            }
            result.add(entity);
        }
        reader.close();
        return result;
    }

    /**
     * 截取方法名字
     * @param methodName
     * @return
     * @date: 2016年3月8日 下午10:33:29
     */
    public static String getName(String methodName) {
        return methodName.substring(0, 1).toUpperCase()
               + methodName.substring(1, methodName.length());
    }

    /**
     * 转换类型
     * @param field
     * @param data
     * @return
     * @throws ParseException
     * @date: 2016年3月8日 下午10:33:37
     */
    public static Object converType(Field field, Object data) throws ParseException {
        if (field.getType().equals(Integer.class)) {
            return Integer.parseInt(data.toString());
        }
        if (field.getType().equals(Double.class)) {
            return new Double(data.toString());
        }
        if (field.getType().equals(Date.class)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(data.toString());
        }
        return data.toString().trim();
    }

}
