package com.netease.timemachine.timeset.util;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.timeset.service.TimeSetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 16:33 2018/7/31
 **/
public class TimeSetUtil{

    public static JSONObject generateTimeSet(String name, String music, List<String> picList){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("music", music);
        jsonObject.put("pictures", picList);
        return jsonObject;
    }
    
    public static List<String> listMapToString(List<HashMap> list){
        List<String> res = new ArrayList<>(list.size());
        for(HashMap map : list){
            Iterator iterator = map.keySet().iterator();
            res.add((String)iterator.next());
        }
        return res;
    }
}
