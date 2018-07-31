package com.netease.timemachine.timeset.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.timeset.service.TimeSetService;
import com.netease.timemachine.timeset.util.CalendarYearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:34 2018/7/30
 **/
@RestController
public class TimeSetController {

    private static final Integer MAX_PICS = 12;

    @Autowired
    private TimeSetService timeSetService;

    @RequestMapping("/generateTimeSet")
    public ResponseEntity generateTimeSet(@RequestParam Long childId){
        String yearMonth = CalendarYearMonth.yearAndMonth();
        JSONObject jsonObject = new JSONObject();
        List<String> listByTime = timeSetService.searchLastMonthByViews(childId);
        if(listByTime != null && !timeSetService.isExist(yearMonth)){
            jsonObject.put(yearMonth, listByTime);
        }
        Map<String,List<String>> mapByLabel = timeSetService.searchLastMonthByLabels(childId);
        Iterator<Map.Entry<String,List<String>>> it = mapByLabel.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, List<String>> entry = it.next();
            String setName = entry.getKey() + yearMonth;
            if(!timeSetService.isExist(setName)){
                jsonObject.put(setName, entry.getValue());
            }
        }
        return ResponseView.success(jsonObject);
    }
}
