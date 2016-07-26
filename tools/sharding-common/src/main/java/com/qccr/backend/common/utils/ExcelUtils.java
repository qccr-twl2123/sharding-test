/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.backend.common.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author wangyz
 * @version $Id: ExcelUtils.java, v 0.1 2016年3月4日 下午1:46:40 wangyz Exp $
 */
public class ExcelUtils {
    /** 
     * Excel 2003 
     */
    private final static String XLS  = "xls";
    /** 
     * Excel 2007 
     */
    private final static String XLSX = "xlsx";

    public static String getVersion(String name) {
        //默认03版本
        if (subName(name).equals(XLSX)) {
            return XLSX;
        } else {
            return XLS;
        }
    }

    public static String subName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        Integer inedx = name.lastIndexOf(".");
        if (inedx < 0) {
            return null;
        }
        return name.substring(inedx + 1, name.length());
    }

    /**
     * 导入时，根据每行数据生成json格式字符串
     * @param file
     * @return
     * @date: 2016年3月4日 下午2:33:21
     */
    public static String getJsonList(File file, Class<?> cls) throws Exception {
        //获得所有属性
        Field[] fields = cls.getDeclaredFields();
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet st = workbook.getSheet(0);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int rs = st.getColumns();
        int rows = st.getRows();
        for (int i = 1; i < rows; i++) {
            sb.append("{");
            for (int j = 0; j < rs; j++) {
                Cell cell = st.getCell(j, i);
                Object value = getValue(cell);
                if (fields[j].getType() == Integer.class) {
                    sb.append("\"" + fields[j].getName() + "\"" + ": " + value + ",");
                } else {
                    sb.append("\"" + fields[j].getName() + "\"" + ":" + "\"" + value + "\",");
                }
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            sb.append("},");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        sb.append("]");
        return sb.toString();
    }

    /**
     * 直接生成list对象
     * @param file
     * @return
     * @date: 2016年3月4日 下午2:33:21
     */
    public static <E> List<E> getList(File file, Class<E> cls) throws Exception {
        return JSONObject.parseArray(getJsonList(file, cls), cls);
    }

    /**
     * 获得表单格的值
     * @param cell
     * @return
     * @date: 2016年3月4日 下午4:02:16
     */
    public static Object getValue(Cell cell) {
        if (cell.getType() == CellType.EMPTY) {
            return "";
        }
        if (cell.getType() == CellType.NUMBER) {
            NumberCell numberCell = (NumberCell) cell;
            return numberCell.getValue();
        }
        if (cell.getType() == CellType.DATE) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateCell dateCell = (DateCell) cell;
            return sdf.format(dateCell.getDate());
        }
        return cell.getContents();
    }
}
