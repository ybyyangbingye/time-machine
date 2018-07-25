package com.netease.timemachine.milestone.controller;

import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.milestone.dto.MilestoneDTO;
import com.netease.timemachine.milestone.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ChildService childService;

    /**
     * 创建里程碑
     * @param request
     * @param milestoneDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addMilestone(HttpServletRequest request, @RequestBody MilestoneDTO milestoneDTO) {
        milestoneService.addMilestone(milestoneDTO);
        return ResponseView.success("", "添加成功");
    }

    /**
     * 获取里程碑列表
     * @param request
     * @param childId
     * @return
     */
    @RequestMapping(value = "/{child_id}", method = RequestMethod.GET)
    public ResponseEntity getMilestoneList(HttpServletRequest request, @PathVariable("child_id") long childId) {
        ChildDTO childDTO = childService.selectChildById(childId);
        List<MilestoneDTO> milestoneDTOList = milestoneService.getMilestoneList(childId);
        for(MilestoneDTO milestoneDTO: milestoneDTOList) {
            Date nowDate = milestoneDTO.getTime();
            int age = nowDate.getYear() - childDTO.getBirthDate().getYear();
            milestoneDTO.setChildAge(age);
        }

        return ResponseView.success(milestoneDTOList);
    }
}
