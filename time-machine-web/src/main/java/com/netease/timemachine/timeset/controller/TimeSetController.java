package com.netease.timemachine.timeset.controller;

import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.timeset.dto.TimeSetDTO;
import com.netease.timemachine.timeset.service.TimeSetService;
import com.netease.timemachine.timeset.util.CalendarYearMonth;
import com.netease.timemachine.timeset.util.TimeSetUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:34 2018/7/30
 **/
@RestController
@RequestMapping("/timeset")
public class TimeSetController {

    @Resource
    private TimeSetService timeSetService;

    /**
     * 查询是否有相应的时光集生成，前端一直在询问
     * 没有创建孩子，给默认时光集
     * 有孩子的话，去数据库拉取满足条件的时光集，判断表timeset是否存在该时光集
     * 若不存在，则添加到表timeset和resource中
     * 最后，统一拉取所有的新旧时光集
     * 如果没有，则返回默认时光集
     * @param childId
     * @return
     */
    @RequestMapping(value = "/return",method = RequestMethod.POST)
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    public ResponseEntity returnTimeSet(@RequestParam(required = false) Long childId){
        List<TimeSetDTO> list = new ArrayList<>();
        if(childId == null){
            list.add(TimeSetUtil.generateDefault());
            list.add(TimeSetUtil.generateDefault10000());
            list.add(TimeSetUtil.generateDefault10001());
            return ResponseView.success(list);
        }
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
        list = timeSetService.selectTimeSetDetail(childId);
        if(!CollectionUtils.isEmpty(list)) {
            return ResponseView.success(list);
        }else {
            list.add(TimeSetUtil.generateDefault());
            list.add(TimeSetUtil.generateDefault10000());
            list.add(TimeSetUtil.generateDefault10001());
            return ResponseView.success(list);
        }
    }

    @RequestMapping(value = "/generate",method = RequestMethod.POST)
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    public ResponseEntity generateTimeSet(@RequestParam(required = false) Long setId){
        if(setId == null || setId == 0){
            return ResponseView.success(TimeSetUtil.generateDefault());
        }
        if(setId.equals(10000L)){
            return ResponseView.success(TimeSetUtil.generateDefault10000());
        }
        if(setId.equals(10001L)){
            return ResponseView.success(TimeSetUtil.generateDefault10001());
        }
        TimeSetDTO timeSetDTO = timeSetService.selectTimeSetBysetId(setId);
        if(timeSetDTO != null) {
            List<String> pics = timeSetService.selectTimeSetResources(setId);
            if(!CollectionUtils.isEmpty(pics)) {
                Collections.reverse(pics);
                timeSetDTO.setSetId(setId);
                timeSetDTO.setPictures(pics);
                timeSetDTO.setMusicUrl(timeSetService.musicRanByTimeSet());
                return ResponseView.success(timeSetDTO);
            }
        }
        return ResponseView.success(TimeSetUtil.generateDefault());
    }
}
