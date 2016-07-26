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
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.hash.HashCode;

/**
 * 客户端请求状态码构造类
 * @author Carl.xu
 * @version $Id: WebWebStateCode.java,v 0.1 2015年12月20日 下午1:47:42 Carl.xu Exp $
 */
public class WebState implements Serializable {
	private static final long serialVersionUID = -4444773798858422121L;
	/* 状态注册 */
	private final static Map<Integer, WebState> lookup = Maps.newHashMap();
	/* 状态码值 */
    private Integer code;
    /* 状态描述 */
    private String desc;

    public WebState(Integer code, String desc) {
    	this.code = code;
        this.desc = desc;
        //重复状态码侦测
        if(code != null && lookup.put(code, this) != null) {
        	throw new IllegalArgumentException(
    				String.format("duplicated code[%d]", code));
        }
    }

    /**
     * 根据状态码找到状态码类
     * 
     * @param code 状态码
     * @param clazz 要获取的stateCode的归属类，命名规范为：[APPID]WebStateCode
     * 
     * @return 状态码类实例
     */
    public static WebState get(int code, Class<?> clazz) {
    	register(clazz);
    	WebState stateCode = lookup.get(code);
    	/* 无效状态码 */
    	if(stateCode == null) { 
    		throw new IllegalArgumentException(
    				String.format("invalid code[%d]", code)); 
    	}

    	return stateCode;
    }

    /**
     * 激活状态码归集类
     *    
     * @param clazz 状态码归集类 
     * 
     * @author: huangkai
     * @date: 2015年9月10日 下午5:20:35
     */
    public static void register(Class<?> clazz) {
    	try {
			Class.forName(clazz.getName());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
    }

    /**
     * 状态码判定
     *   
     * @param code  状态码值
     * @param stateCode 要匹配的状态码类型
     * @return true 如果状态码成功匹配
     * @date: 2015年9月10日 下午5:21:32
     */
    public static boolean is(int code, WebState stateCode) {
    	if(stateCode == null) { 
    		throw new IllegalArgumentException("stateCode is null");
    	}
    	
    	return stateCode.equals(get(code, WebState.class));
    }

    /* 状态码 */
    public int getCode() {
        return code;
    }
    /* 状态码描述 */
    public String getDesc() {
        return desc;
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null)  {  
			return false;  
		}

		if (getClass() != obj.getClass()) {  
			return false;
		}

		final WebState other = (WebState) obj;  

		return Objects.equal(this.code, other.code); 
	}

	@Override
	public int hashCode() {
		return HashCode.fromInt(code).asInt();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
						  .add("code", this.code)
						  .add("desc", this.desc)
						  .toString();
	}
}
