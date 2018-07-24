package com.netease.timemachine.momment.controller;

import com.netease.timemachine.momment.service.MommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 11:18
 */

@RestController
@RequestMapping("/momment")
public class MommentController {

    @Autowired
    private MommentService mommentService;

    @RequestMapping(value = "/getMomments", method = RequestMethod.POST)
    public List<Null> getMomments(@RequestParam(required = false) Long userId,
                                  @RequestParam(required = false) Long childId,
                                  @RequestParam(required = false) Integer currentPage) {

        return null;
    }
}
