package com.netease.timemachine.timeset.util;

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
            "http://time-machine.nos-eastchina1.126.net/default/timeset/1.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/2.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/3.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/4.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/5.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/6.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/7.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/8.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/9.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/10.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/11.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/12.jpg"
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
        List<String> pictures = Arrays.asList(DEFAULT_PICS);
        Collections.reverse(pictures);
        TimeSetDTO timeSetDTO = new TimeSetDTO("时光集", null, pictures,
                DEFAULT_MUSIC);
        timeSetDTO.setSetId(0L);
        return timeSetDTO;
    }
}
