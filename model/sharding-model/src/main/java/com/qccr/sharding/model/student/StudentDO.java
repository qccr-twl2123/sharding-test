/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.dinner.model.student;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author xierongli
 * @version $Id: Student.java, v 0.1 2016年7月25日 上午11:43:22 xierongli Exp $
 */
public class StudentDO implements Serializable{
 /**  */
    private static final long serialVersionUID = 5138190933517940862L;

    private Integer id;
    
    private Integer studentId;
    
    private String name;
    
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
    
    
    public String toString() {
        return ToStringBuilder.reflectionToString (this, ToStringStyle.JSON_STYLE);
      }

}
