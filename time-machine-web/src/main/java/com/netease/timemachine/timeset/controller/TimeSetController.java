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
     * 查询是否有相应的时光集生成，前台一直在询问
     * @param childId
     * @return
     */
    @RequestMapping(value = "/return",method = RequestMethod.POST)
    public ResponseEntity returnTimeSet(@RequestParam Long childId){
        String yearMonth = CalendarYearMonth.yearAndMonth();
        List<TimeSetDTO> list = new ArrayList<>();
        List<HashMap> listByTime = timeSetService.searchLastMonthByViews(childId);
        if(listByTime != null && !timeSetService.isExist(yearMonth, childId)){
            TimeSetDTO timeSetDTO = new TimeSetDTO(yearMonth, childId, TimeSetUtil.listMapToString(listByTime),
                    timeSetService.musicRanByTimeSet());
            list.add(timeSetDTO);
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
                    list.add(timeSetDTO);
                }
            }
        }
        /**去拉取已经生成的时光集*/
        List<TimeSetDTO> oldList = timeSetService.selectTimeSetDetail(childId);
        if(!CollectionUtils.isEmpty(oldList) && !CollectionUtils.isEmpty(list)){
            list.addAll(oldList);
        }
        return ResponseView.success(list);
    }

    /**
     * 前端点开某个壳子，生成真实的时光集
     * @param timeSetDTO
     * @return
     */
    @RequestMapping("/generate")
    public ResponseEntity generateTimeSet(@RequestBody TimeSetDTO timeSetDTO){
        if(!timeSetService.isExist(timeSetDTO.getSetName(), timeSetDTO.getChildId())){
            Long setId = timeSetService.addTimeSet(timeSetDTO);
            timeSetService.addTimeSetToResource(timeSetDTO.getPictures(), setId);
        }
        return ResponseView.success(null, "时光集生成成功");
    }
}
