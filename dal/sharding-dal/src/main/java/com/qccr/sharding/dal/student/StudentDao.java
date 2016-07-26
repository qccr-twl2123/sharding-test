/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sharding.dal.student;

import java.util.List;

import com.qccr.sharding.model.student.StudentDO;

/**
 * 
 * @author xierongli
 * @version $Id: StudentDao.java, v 0.1 2016年7月25日 上午11:47:14 xierongli Exp $
 */
public interface StudentDao {
    Integer insert(StudentDO s);
    
    List<StudentDO> findAll();
    
    List<StudentDO> findByStudentIds(List<Integer> studentIds);
}
