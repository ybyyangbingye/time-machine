package com.netease.timemachine.timeset.util;

import java.util.Calendar;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:42 2018/7/30
 **/
public class CalendarYearMonth {
    private static final Calendar cal = Calendar.getInstance();

    public static String yearAndMonth(){
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        return year + " 年 " + month + " 月";
    }
}
