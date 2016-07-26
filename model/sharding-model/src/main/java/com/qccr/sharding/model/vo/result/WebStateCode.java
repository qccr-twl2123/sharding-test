/*
 * 汽车超人.
 * 2015 qccr-All rights reserved 
 *
 * Author     : Carl.xu
 * Version    : 1.0
 * Create Date: 2015年12月20日
 */
package com.qccr.dinner.model.vo.result;

/**
 * 客户端请求状态码定义
 * @author Carl.xu
 * @version $Id: WebCommWebStateCode.java,v 0.1 2015年12月20日 下午1:54:59 Carl.xu Exp $
 */
public interface WebStateCode {
    /** 通用状态码  **/
    WebState SUCCESS            = new WebState(0, "成功");
    WebState ILLEGAL_PARAMETER  = new WebState(-102, "无效的参数");
    WebState DUPLICATE_INVOKE   = new WebState(-108, "重复调用");
    WebState PARAMETER_LACK     = new WebState(-101, "请求参数缺失");
    WebState BODY_LACK          = new WebState(-100, "post请求消息体缺失");
    WebState ILLEGAL_TOKEN      = new WebState(-106, "非法的token");
    WebState NO_RELATED_DATA    = new WebState(-107, "无相关数据");
    WebState FAILED             = new WebState(-110, "操作失败");
    WebState OPERATE_UP_LIMIT   = new WebState(-111, "操作上限");
    WebState DATA_EMPTY         = new WebState(-302, "返回数据为空");
    WebState PARSE_ERROR        = new WebState(-306, "参数解析失败");
    WebState DATA_ERROR         = new WebState(-307, "返回数据格式错误");
    WebState ILLEGAL_SIGN       = new WebState(-701, "无效的签名");
    WebState OVERDUE_TIME       = new WebState(-702, "超时的时间戳");
    WebState ERROR_REQUEST      = new WebState(-995, "错误的请求");
    WebState DB_ERROR           = new WebState(-996, "数据库异常");
    WebState INNER_SERVER_ERROR = new WebState(-997, "非正常的内部服务");
}
