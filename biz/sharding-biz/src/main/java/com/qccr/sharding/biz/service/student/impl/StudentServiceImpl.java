/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.dinner.biz.service.booking.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qccr.dinner.biz.service.booking.StudentService;
import com.qccr.dinner.dal.student.StudentDao;
import com.qccr.dinner.model.student.StudentDO;

/**
 * 
 * @author xierongli
 * @version $Id: StudentServiceImpl.java, v 0.1 2016年7月25日 下午1:49:30 xierongli Exp $
 */
@Service("tudentService")
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentDao studentDao;
    @Override
    public boolean insert(StudentDO student) {
        return studentDao.insert(student) > 0? true : false;
    }

}
