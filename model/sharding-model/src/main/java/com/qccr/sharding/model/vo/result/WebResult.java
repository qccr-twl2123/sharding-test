/*
 * 汽车超人.
 * 2015 qccr-All rights reserved 
 *
 * Author     : Carl.xu
 * Version    : 1.0
 * Create Date: 2015年12月20日
 */
package com.qccr.dinner.model.vo.result;

import java.io.Serializable;

/**
 * 客户端请求返回结果集（通用）
 * 
 * @author Carl.xu
 * @version $Id: WebResult.java,v 0.1 2015年12月20日 下午1:39:48 Carl.xu Exp $
 */
public class WebResult<T> implements Serializable {
    private static final long serialVersionUID = -8004892467677559723L;

    /* 结果数据 */
    private T data;

    /* 状态码 */
    private WebState stateCode;

    /* 状态描述辅助信息 */
    private String statusText;

    private Integer total;
    
    public WebResult() {
        super();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return stateCode.getCode() >= 0;
    }

    public boolean isFailed() {
        return !isSuccess();
    }

    public WebState getStateCode() {
        return stateCode;
    }

    public void setStateCode(WebState stateCode) {
        this.stateCode = stateCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    /**
     * Getter method for property <tt>total</tt>.
     * 
     * @return property value of total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Setter method for property <tt>total</tt>.
     * 
     * @param total value to be assigned to property total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("WebResult [data=%s, stateCode=%s, statusText=%s]", data, stateCode,
            statusText);
    }
}
