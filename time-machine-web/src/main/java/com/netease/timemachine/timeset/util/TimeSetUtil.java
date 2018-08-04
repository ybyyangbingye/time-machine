package com.netease.timemachine.timeset.util;

import com.netease.timemachine.timeset.dao.TimeSetDao;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.service.TimeSetService;

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
        }
        return res;
    }

    /**
     * 新增一条时光集，同时插入timeset和resource表
     * @param timeSetService
     * @param timeSetDTO
     */
    public static void addTimeSetAndResource(TimeSetService timeSetService, TimeSetDTO timeSetDTO){
        Long setId = timeSetService.addTimeSet(timeSetDTO);
        for(String picture : timeSetDTO.getPictures()) {
            timeSetService.addTimeSetToResource(picture, setId);
        }
    }
}
