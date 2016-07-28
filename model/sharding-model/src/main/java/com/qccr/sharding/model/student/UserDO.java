/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sharding.model.student;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author xierongli
 * @version $Id: User.java, v 0.1 2016年7月25日 上午11:43:37 xierongli Exp $
 */
public class UserDO implements Serializable{
    /**  */
    private static final long serialVersionUID = 3653919519029648245L;

    private Integer id;
    
    private Integer userId;
    
    private String name;
    
    private Integer age;
    
    private Date  createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    
    
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String toString() {
        return ToStringBuilder.reflectionToString (this, ToStringStyle.JSON_STYLE);
      }

    
    
}
