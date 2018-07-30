package com.netease.timemachine.moment.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.moment.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 9:32 2018/7/26
 **/
@RequestMapping("/location")
@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/posByPoint",method = RequestMethod.POST)
    public ResponseEntity searchPosByPoint(@RequestParam("location") String location,
                                           @RequestParam("page_size") Integer page_size,
                                           @RequestParam("page_index") Integer page_index){
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = locationService.searchPosByPoint(location, page_size, page_index);
            jsonObject.put("locations", jsonArray);
        }catch (Exception e){
            return ResponseView.fail(1001,"获取位置信息失败");
        }
        return ResponseView.success(jsonObject);
    }

    @RequestMapping(value = "/posByKey",method = RequestMethod.POST)
    public ResponseEntity searchPosByKey(@RequestParam("location") String location,
                                         @RequestParam("keyword") String keyword,
                                         @RequestParam("page_size") Integer page_size,
                                         @RequestParam("page_index") Integer page_index){
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = locationService.searchPosByKeyWord(location, keyword, page_size, page_index);
            jsonObject.put("locations", jsonArray);
        }catch (Exception e){
            return ResponseView.fail(1001,"获取位置信息失败");
        }
        return ResponseView.success(jsonObject);
    }

}
