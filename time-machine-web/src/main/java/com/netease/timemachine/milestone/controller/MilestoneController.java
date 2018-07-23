package com.netease.timemachine.milestone.controller;

import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.milestone.dto.MilestoneDTO;
import com.netease.timemachine.milestone.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:15
 */
@RestController
@RequestMapping(value = "/milestone")
public class MilestoneController {

    @Autowired
    private MilestoneService milestoneService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity submitFeedback(HttpServletRequest request, @RequestBody MilestoneDTO milestoneDTO) {
        milestoneService.addMilestone(milestoneDTO);
        return ResponseView.success("", "添加成功");
    }
}
