package com.netease.timemachine.moment.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.moment.service.LabelService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/27 10:34
 */

@RestController
@RequestMapping("/label")
public class LabelController {

    @Resource
    private LabelService labelService;


    @RequestMapping(value = "/getLabels", method = RequestMethod.POST)
    public ResponseEntity getLabels(@RequestParam Long userId, @RequestParam Long childId) {
        List<String> res1 = labelService.getHistoryLabels(userId,childId);
        List<String> res2 = labelService.getFamilyLabels(userId,childId);
        List<String> res3 = labelService.getRecommendLabels(userId,childId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("history", res1);
        jsonObject.put("family", res2);
        jsonObject.put("recommend", res3);
        return ResponseView.success(jsonObject);
    }

    @RequestMapping(value = "/addLabels", method = RequestMethod.POST)
    public ResponseEntity addLabels(@RequestParam Long userId, @RequestParam Long childId,
                                    @RequestParam Long momentId, @RequestParam List<String> labels) {
        labelService.addLabels(userId,childId,momentId,labels);
        //return ResponseView.success(null,"添加成功");
        return null;
    }
}
