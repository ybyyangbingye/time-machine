package com.netease.timemachine.timeset.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.timeset.service.TimeSetService;
import com.netease.timemachine.timeset.util.CalendarYearMonth;
import com.netease.timemachine.timeset.util.TimeSetUtil;
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

    /**
     * 申请，查询是否有相应的
     * @param childId
     * @return
     */
    @RequestMapping("/generateTimeSet")
    public ResponseEntity generateTimeSet(@RequestParam Long childId){
        String yearMonth = CalendarYearMonth.yearAndMonth();
        JSONObject jsonObject = new JSONObject();
        List<HashMap> listByTime = timeSetService.searchLastMonthByViews(childId);
        if(listByTime != null && !timeSetService.isExist(yearMonth)){
            String music = timeSetService.resourceRanByTimeSet();
            JSONObject time = TimeSetUtil.generateTimeSet(yearMonth, music, TimeSetUtil.listMapToString(listByTime));
            jsonObject.put("time", time);
        }
        Map<String,List<String>> mapByLabel = timeSetService.searchLastMonthByLabels(childId);
        if(!CollectionUtils.isEmpty(mapByLabel)){
            Iterator<Map.Entry<String,List<String>>> it = mapByLabel.entrySet().iterator();
            JSONArray jsonArray = new JSONArray();
            while (it.hasNext()){
                Map.Entry<String, List<String>> entry = it.next();
                String labelName = entry.getKey() + yearMonth;
                if(!timeSetService.isExist(labelName)){
                    String music = timeSetService.resourceRanByTimeSet();
                    JSONObject label = TimeSetUtil.generateTimeSet(labelName, music, entry.getValue());
                    jsonArray.add(label);
                }
            }
            jsonObject.put("label", jsonArray);
        }
        return ResponseView.success(jsonObject);
    }
}
