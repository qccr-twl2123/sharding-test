/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sharding.biz.service.student.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qccr.sharding.biz.service.student.UserService;
import com.qccr.sharding.dal.student.StudentDao;
import com.qccr.sharding.dal.student.UserDao;
import com.qccr.sharding.model.student.UserDO;



/**
 * 
 * @author xierongli
 * @version $Id: UserServiceImpl.java, v 0.1 2016年7月25日 下午1:54:34 xierongli Exp $
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    public UserDao userDao;
    
    @Resource
    public StudentDao studentDao;

    public boolean insert(UserDO u) {
        return userDao.insert(u) > 0 ? true :false;
    }

    public List<UserDO> findAll() {
        return userDao.findAll();
    }

    public List<UserDO> findByUserDOIds(List<Integer> ids) {
        return userDao.findByUserIds(ids);
    }

//    @Transactional(propagation=Propagation.REQUIRED)
//    public void transactionTestSucess() {
//        UserDO u = new UserDO();
//        u.setUserId(13);
//        u.setAge(25);
//        u.setName("war3 1.27");
//        userDao.insert(u);
//        
//        StudentDO student = new StudentDO();
//        student.setStudentId(21);
//        student.setAge(21);
//        student.setName("hehe");
//        studentDao.insert(student);
//    }
//
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void transactionTestFailure() throws IllegalAccessException {
//        UserDO u = new UserDO();
//        u.setUserId(13);
//        u.setAge(25);
//        u.setName("war3 1.27 good");
//        userDao.insert(u);
//        
//        StudentDO student = new StudentDO();
//        student.setStudentId(21);
//        student.setAge(21);
//        student.setName("hehe1");
//        studentDao.insert(student);
//        throw new IllegalAccessException();
//    }
}
