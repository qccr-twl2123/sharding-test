/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sharding.biz.service.student.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qccr.sharding.biz.service.student.StudentService;
import com.qccr.sharding.dal.student.StudentDao;
import com.qccr.sharding.model.student.StudentDO;

	

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
