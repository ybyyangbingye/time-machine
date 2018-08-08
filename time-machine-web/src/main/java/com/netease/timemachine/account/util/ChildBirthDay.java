package com.netease.timemachine.account.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:53 2018/7/26
 **/
public class ChildBirthDay {

    public static String getAge(Date date) {
        if(date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(date);
        String[] data = str.split("-");
        if (data.length < 3) {
            return "";
        }
        Calendar birthday = new GregorianCalendar(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2]));
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH) + 1 - birthday.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        //按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
        if (day < 0) {
            month -= 1;
            //得到上一个月，用来得到上个月的天数。
            now.add(Calendar.MONTH, -1);
            day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (month < 0) {
            month = (month + 12) % 12;
            year--;
        }
        StringBuffer tag = new StringBuffer();
        if (year > 0) {
            if(month == 0 && day == 0){
                tag.append(year + "周岁");
            }else {
                tag.append(year + "岁");
            }
        }
        if (month > 0) {
            tag.append(month + "个月");
        }
        if (day > 0) {
            if(month == 0) {
                tag.append("第" + day + "天");
            }
            if(month > 0){
                tag.append(day + "天");
            }
        }
        if (year == 0 && month == 0 && day == 0) {
            tag.append("今日出生");
        }
        return tag.toString();
    }
    public static Integer getChildMonths(Date date){
        if(date == null){
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(date);
        String[] data = str.split("-");
        if (data.length < 3) {
            return 0;
        }
        Calendar birthday = new GregorianCalendar(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2]));
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH) + 1 - birthday.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        //按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
        if (day < 0) {
            month -= 1;
            //得到上一个月，用来得到上个月的天数。
            now.add(Calendar.MONTH, -1);
            day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return year*12+month;
    }

}
