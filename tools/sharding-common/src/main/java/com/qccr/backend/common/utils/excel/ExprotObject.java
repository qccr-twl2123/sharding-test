/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.backend.common.utils.excel;

import java.util.List;

/**
 * 
 * @author wangyz
 * @version $Id: ExprotEntity.java, v 0.1 2016年3月7日 上午10:58:08 wangyz Exp $
 */
public class ExprotObject {

    /**
     * excel名字
     */
    private String name;
    /**
     * 表单名字
     */
    private String sheetName;

    /**
     * 表单头
     */
    private List<String> columns;

    /**
     * 导出属性
     */
    private List<String> valueColumns;

    /**
     * 数据
     */
    private List<? extends Object> data;

    /**
     * 是否打印表单头
     */
    private Boolean isTitleColumns = Boolean.TRUE;

    /**
     * Getter method for property <tt>sheetName</tt>.
     * 
     * @return property value of sheetName
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * Setter method for property <tt>sheetName</tt>.
     * 
     * @param sheetName value to be assigned to property sheetName
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * Getter method for property <tt>columns</tt>.
     * 
     * @return property value of columns
     */
    public List<String> getColumns() {
        return columns;
    }

    /**
     * Setter method for property <tt>columns</tt>.
     * 
     * @param columns value to be assigned to property columns
     */
    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    /**
     * Getter method for property <tt>valueColumns</tt>.
     * 
     * @return property value of valueColumns
     */
    public List<String> getValueColumns() {
        return valueColumns;
    }

    /**
     * Setter method for property <tt>valueColumns</tt>.
     * 
     * @param valueColumns value to be assigned to property valueColumns
     */
    public void setValueColumns(List<String> valueColumns) {
        this.valueColumns = valueColumns;
    }

    /**
     * Getter method for property <tt>data</tt>.
     * 
     * @return property value of data
     */
    public List<? extends Object> getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     * 
     * @param data value to be assigned to property data
     */
    public void setData(List<? extends Object> data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>isTitleColumns</tt>.
     * 
     * @return property value of isTitleColumns
     */
    public Boolean getIsTitleColumns() {
        return isTitleColumns;
    }

    /**
     * Setter method for property <tt>isTitleColumns</tt>.
     * 
     * @param isTitleColumns value to be assigned to property isTitleColumns
     */
    public void setIsTitleColumns(Boolean isTitleColumns) {
        this.isTitleColumns = isTitleColumns;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    public ExprotObject() {

    }
}
