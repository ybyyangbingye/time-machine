package com.netease.timemachine.timeset.util;

import java.util.Calendar;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:42 2018/7/30
 **/
public class CalendarYearMonth {

    private static final Calendar cal = Calendar.getInstance();

    /**
     * 返回前一个月的年月信息
     * @return
     */
    public static String yearAndMonth(){
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH );
        return year + "年" + month + "月";
    }

    public static void main(String[] args) {
        System.out.println(yearAndMonth());
    }
}
