/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.backend.biz.test;

import javax.annotation.Resource;

import org.junit.Test;
/**
 * 
 * @author xierongli
 * @version $Id: ShardingJdbcMybatisTest.java, v 0.1 2016年7月25日 下午2:39:54 xierongli Exp $
 */
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qccr.dinner.biz.service.booking.StudentService;
import com.qccr.dinner.biz.service.booking.UserService;
import com.qccr.dinner.model.student.UserDO;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-database.xml",
        "classpath*:spring-sharding.xml" })
public class ShardingJdbcMybatisTest {
    @Resource
    public UserService userService;
    
    @Resource
    public StudentService studentService;

    @Test
    public void testUserInsert() {
        UserDO u = new UserDO();
        u.setUserId(11);
        u.setAge(25);
        u.setName("github");
        Assert.assertEquals(userService.insert(u), true);
    }
}
