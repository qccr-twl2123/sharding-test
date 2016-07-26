/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sharding.biz.service.student;

import java.util.List;

import com.qccr.sharding.model.student.UserDO;

/**
 * 
 * @author xierongli
 * @version $Id: UserDOService.java, v 0.1 2016年7月25日 下午1:54:27 xierongli Exp $
 */
public interface UserService {
    public boolean insert(UserDO u);
    
    public List<UserDO> findAll();
    
    public List<UserDO> findByUserDOIds(List<Integer> ids);
    
   // public void transactionTestSucess();
    
   // public void transactionTestFailure() throws IllegalAccessException;
}
