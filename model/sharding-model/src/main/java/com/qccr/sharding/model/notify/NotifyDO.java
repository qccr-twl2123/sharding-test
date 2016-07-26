/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.dinner.model.notify;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author xierongli
 * @version $Id: NotifyDO.java, v 0.1 2016年6月30日 下午2:31:26 xierongli Exp $
 */
public class NotifyDO  implements Serializable{

    
    /** uid */
    private static final long serialVersionUID = -8216123775796131742L;
    private Integer id;
    private String content;
    private Integer type;
    private Integer target;
    private Integer targetType;
    private String action;
    private Integer sender;
    private Date createTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getTarget() {
        return target;
    }
    public void setTarget(Integer target) {
        this.target = target;
    }
    public Integer getTargetType() {
        return targetType;
    }
    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public Integer getSender() {
        return sender;
    }
    public void setSender(Integer sender) {
        this.sender = sender;
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
