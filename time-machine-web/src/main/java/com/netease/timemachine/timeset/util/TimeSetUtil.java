package com.netease.timemachine.timeset.util;

import com.netease.timemachine.timeset.dao.TimeSetDao;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.service.TimeSetService;

import java.util.*;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 16:33 2018/7/31
 **/
public class TimeSetUtil{

    /**时光集默认图片*/
    public static final String[] DEFAULT_PICS = new String[]{
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p1.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p2.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p3.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p4.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p5.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p6.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p7.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p8.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p9.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p10.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p11.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/p12.png"
    };

    /**时光集默认音乐*/
    public static final String DEFAULT_MUSIC = "http://time-machine.nos-eastchina1.126.net/music/1-13%20%E6%A0%91%E6%B0%B7%E3%81%AE%E8%BE%89%E3%81%8D.m4a";

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

    public static TimeSetDTO generateDefault(){
        TimeSetDTO timeSetDTO = new TimeSetDTO("时光集", null, Arrays.asList(DEFAULT_PICS),
                DEFAULT_MUSIC);
        return timeSetDTO;
    }

    public static String returnDefaultPics(){
        return DEFAULT_PICS[0];
    }
}
