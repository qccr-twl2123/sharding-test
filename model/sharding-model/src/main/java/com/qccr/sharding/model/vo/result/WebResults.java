/*
 * 汽车超人.
 * 2015 qccr-All rights reserved 
 *
 * Author     : Carl.xu
 * Version    : 1.0
 * Create Date: 2015年12月20日
 */
package com.qccr.dinner.model.vo.result;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 客户端请求结果集辅助工具类
 * @author Carl.xu
 * @version $Id: WebWebResults.java,v 0.1 2015年12月20日 下午1:51:12 Carl.xu Exp $
 */
public abstract class WebResults {
    /** 成功结果 */
    public static <T> WebResult<T> newSuccessWebResult(T data) {
        return newSuccessWebResult(data, WebStateCode.SUCCESS.getDesc());
    }

    /** 成功结果 */
    public static <T> WebResult<T> newSuccessWebResult(T data, String statusText) {
        return newWebResult(data, WebStateCode.SUCCESS, statusText);
    }

    /** 成功结果 */
    public static <T> WebResult<T> newSuccessWebResult(T data, Integer total, String statusText) {
        return newWebResult(data, WebStateCode.SUCCESS, total, statusText);
    }

    /** 没有数据回传的失败结果 */
    public static <T> WebResult<T> newFailedWebResult(WebState failedCode) {
        return newFailedWebResult(null, failedCode);
    }

    /** 没有数据回传的失败结果 */
    public static <T> WebResult<T> newFailedWebResult(WebState failedCode, String statusText) {
        return newFailedWebResult(null, failedCode, statusText);
    }

    /** 有数据回传的失败结果 */
    public static <T> WebResult<T> newFailedWebResult(T data, WebState failedCode) {
        return newFailedWebResult(data, failedCode, failedCode.getDesc());
    }

    /** 有数据回传的失败结果 */
    public static <T> WebResult<T> newFailedWebResult(T data, WebState failedCode,
                                                      String statusText) {
        checkNotNull(failedCode);
        checkArgument(failedCode.getCode() < 0,
            "invalid code, for failed result, code must be negative integers");

        return newWebResult(data, failedCode, statusText);
    }

    /** 仅返回状态码*/
    public static <T> WebResult<T> newWebResult(WebState code) {
        return newWebResult(null, code, code.getDesc());
    }

    /** 有数据回传的结果 */
    public static <T> WebResult<T> newWebResult(T data, WebState failedCode, String statusText) {
        WebResult<T> result = new WebResult<T>();
        result.setData(data);
        result.setStateCode(failedCode);
        result.setStatusText(statusText);
        return result;
    }

    /** 有数据回传的结果 */
    public static <T> WebResult<T> newWebResult(T data, WebState failedCode, Integer total,
                                                String statusText) {
        WebResult<T> result = new WebResult<T>();
        result.setData(data);
        result.setStateCode(failedCode);
        result.setStatusText(statusText);
        result.setTotal(total);
        return result;
    }
}
