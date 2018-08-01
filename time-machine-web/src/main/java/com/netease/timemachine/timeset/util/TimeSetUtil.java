package com.netease.timemachine.timeset.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 16:33 2018/7/31
 **/
public class TimeSetUtil{

    /**
     * map集合转string集合
     * @param list
     * @return
     */
    public static List<String> listMapToString(List<HashMap> list){
        List<String> res = new ArrayList<>(list.size());
        for(HashMap map : list){
            Iterator iterator = map.values().iterator();
            res.add((String)iterator.next());
            break;
        }
        return res;
    }
}
