/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.dinner.model.query;

import java.util.Date;

/**
 * 
 * @author wangyz
 * @version $Id: BaseQueryOption.java, v 0.1 2016年2月22日 下午5:35:03 wangyz Exp $
 */
public class BaseQueryOption {

    /**
     * 起始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * ID编号
     */
    private Integer id;

    /**
     * 页面起始数目  默认为0
     */
    private Integer pageStart = 0;

    /**
     * 页面数据数量
     */
    private Integer pageLimit;

    /**
     * 页目数
     */
    @Deprecated
    private Integer pageNum;

    /**
     * 页面大小
     */
    @Deprecated
    private Integer pageSize;

    /**
     * Getter method for property <tt>startDate</tt>.
     * 
     * @return property value of startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter method for property <tt>startDate</tt>.
     * 
     * @param startDate value to be assigned to property startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter method for property <tt>endDate</tt>.
     * 
     * @return property value of endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter method for property <tt>endDate</tt>.
     * 
     * @param endDate value to be assigned to property endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>pageStart</tt>.
     * 
     * @return property value of pageStart
     */
    public Integer getPageStart() {
        return pageStart;
    }

    /**
     * Setter method for property <tt>pageStart</tt>.
     * 
     * @param pageStart value to be assigned to property pageStart
     */
    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     * 
     * @return property value of pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     * 
     * @param pageSize value to be assigned to property pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Getter method for property <tt>pageNum</tt>.
     * 
     * @return property value of pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * Setter method for property <tt>pageNum</tt>.
     * 
     * @param pageNum value to be assigned to property pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Getter method for property <tt>pageLimit</tt>.
     * 
     * @return property value of pageLimit
     */
    public Integer getPageLimit() {
        return pageLimit;
    }

    /**
     * Setter method for property <tt>pageLimit</tt>.
     * 
     * @param pageLimit value to be assigned to property pageLimit
     */
    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

}
