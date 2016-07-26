/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.backend.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author wangyz
 * @version $Id: DateUtils.java, v 0.1 2016年1月22日 下午2:51:17 wangyz Exp $
 */
public class DateUtils {
    private static Calendar calendar = Calendar.getInstance();

    /**
     * 获得当天最大时间
     * @param time
     * @return
     * @date: 2016年1月22日 下午2:52:42
     */
    public static Date getDayLastTime(Date time) {
        calendar.setTime(time);
        calendar.add(Calendar.HOUR, 23);
        calendar.add(Calendar.MINUTE, 59);
        calendar.add(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获得下一天
     * @param time
     * @return
     * @date: 2016年1月22日 下午2:52:35
     */
    public static Date getNextDay(Date time) {
        calendar.setTime(time);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
