package com.netease.timemachine.timeset.controller;

import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.service.TimeSetService;
import com.netease.timemachine.timeset.util.CalendarYearMonth;
import com.netease.timemachine.timeset.util.TimeSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:34 2018/7/30
 **/
@RestController
@RequestMapping("/timeset")
public class TimeSetController {

    @Autowired
    private TimeSetService timeSetService;

    /**
     * 查询是否有相应的时光集生成，前端一直在询问
     * 首先去数据库拉取满足条件的时光集，判断表timeset是否存在
     * 若不存在，则加入list，并且添加到表timeset和resource中
     * @param childId
     * @return
     */
    @RequestMapping(value = "/return",method = RequestMethod.POST)
    public ResponseEntity returnTimeSet(@RequestParam Long childId){
        String yearMonth = CalendarYearMonth.yearAndMonth();
        List<HashMap> listByTime = timeSetService.searchLastMonthByViews(childId);
        if(listByTime != null && !timeSetService.isExist(yearMonth, childId)){
            TimeSetDTO timeSetDTO = new TimeSetDTO(yearMonth, childId, TimeSetUtil.listMapToString(listByTime),
                    timeSetService.musicRanByTimeSet());
            TimeSetUtil.addTimeSetAndResource(timeSetService, timeSetDTO);
        }
        Map<String,List<String>> mapByLabel = timeSetService.searchLastMonthByLabels(childId);
        if(!CollectionUtils.isEmpty(mapByLabel)){
            Iterator<Map.Entry<String,List<String>>> it = mapByLabel.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String, List<String>> entry = it.next();
                String labelName = entry.getKey() + "," +  yearMonth;
                if(!timeSetService.isExist(labelName, childId)){
                    TimeSetDTO timeSetDTO = new TimeSetDTO(labelName, childId, entry.getValue(),
                            timeSetService.musicRanByTimeSet());
                    TimeSetUtil.addTimeSetAndResource(timeSetService, timeSetDTO);
                }
            }
        }
        /**去拉取已经生成的时光集*/
        List<TimeSetDTO> list = timeSetService.selectTimeSetDetail(childId);
        if(!CollectionUtils.isEmpty(list)) {
            return ResponseView.success(list);
        }
        return ResponseView.success(null, "还没有时光集生成哟");
    }
}
