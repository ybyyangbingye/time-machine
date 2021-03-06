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
            "http://time-machine.nos-eastchina1.126.net/default/timeset/12.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/11.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/10.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/9.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/8.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/7.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/6.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/5.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/4.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/3.jpg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/2.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset/1.jpeg"
    };

    public static final String[] DEFAULT_PICS2 = new String[]{
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-12.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-11.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-10.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-9.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-8.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-7.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-6.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-5.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-4.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-3.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-2.jpeg",
            "http://time-machine.nos-eastchina1.126.net/default/timeset2/2-1.jpeg"
    };

    public static final String[] DEFAULT_PICS3 = new String[]{
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/12.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/11.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/10.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/9.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/8.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/7.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/6.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/5.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/4.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/3.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/2.png",
            "http://time-machine.nos-eastchina1.126.net/default/timeset3/1.png"
    };

    /**时光集默认音乐*/
    public static final String DEFAULT_MUSIC = "http://time-machine.nos-eastchina1.126.net/music/%E5%8A%A0%E8%97%A4%E9%81%94%E4%B9%9F-%E3%81%8D%E3%82%89%E3%82%81%E3%81%8F%E6%B9%96%E7%95%94%20-%20%E9%93%83%E5%A3%B0(1).mp3";

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
        TimeSetDTO timeSetDTO = new TimeSetDTO("时光集示例", null, pictures,
                DEFAULT_MUSIC);
        timeSetDTO.setSetId(0L);
        return timeSetDTO;
    }

    public static TimeSetDTO generateDefault10000(){
        List<String> pictures = Arrays.asList(DEFAULT_PICS2);
        TimeSetDTO timeSetDTO = new TimeSetDTO("时光集示例", null, pictures,
                DEFAULT_MUSIC);
        timeSetDTO.setSetId(10000L);
        return timeSetDTO;
    }

    public static TimeSetDTO generateDefault10001(){
        List<String> pictures = Arrays.asList(DEFAULT_PICS3);
        TimeSetDTO timeSetDTO = new TimeSetDTO("时光集示例", null, pictures,
                DEFAULT_MUSIC);
        timeSetDTO.setSetId(10001L);
        return timeSetDTO;
    }
}
