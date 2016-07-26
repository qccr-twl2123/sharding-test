/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sharding.dal.student;

import java.util.List;

import com.qccr.sharding.model.student.UserDO;

/**
 * 
 * @author xierongli
 * @version $Id: UserDao.java, v 0.1 2016年7月25日 上午11:48:04 xierongli Exp $
 */
public interface UserDao {
    Integer insert(UserDO u);
    List<UserDO> findAll();
    List<UserDO> findByUserIds(List<Integer> userIds);
}
