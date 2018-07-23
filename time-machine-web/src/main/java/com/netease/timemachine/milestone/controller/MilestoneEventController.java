package com.netease.timemachine.milestone.controller;

import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.common.service.ResourceService;
import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import com.netease.timemachine.milestone.service.MilestoneEventService;
import com.netease.timemachine.milestone.vo.MilestoneEventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 里程碑里的事件、图片管理
 *
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午5:34
 */
@RestController
@RequestMapping(value = "/milestone_event")
public class MilestoneEventController {

    @Autowired
    private MilestoneEventService milestoneEventService;
    @Autowired
    private ResourceService milestoneEventImageService;

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity addMilestoneEvent(HttpServletRequest request, @RequestBody MilestoneEventVO milestoneEventVO) {
        // 添加事件到主表
        MilestoneEventDTO milestoneEventDTO = new MilestoneEventDTO();
        milestoneEventDTO.setMilestoneId(milestoneEventVO.getMilestoneId());
        milestoneEventDTO.setLocation(milestoneEventVO.getLocation());
        milestoneEventDTO.setTime(milestoneEventVO.getTime());
        milestoneEventDTO.setGmtCreate(new Date());
        milestoneEventService.addMilestoneEvent(milestoneEventDTO);

        // 添加事件关联的图片
        ResourceDTO milestoneEventImageDTO = new ResourceDTO();
        milestoneEventImageDTO.setParentId(milestoneEventDTO.getId());
        milestoneEventImageDTO.setGmtCreate(new Date());
        milestoneEventImageDTO.setType(milestoneEventVO.getType());
        List<String> images = milestoneEventVO.getImages();
        for(String image: images) {
            milestoneEventImageDTO.setResourceObj(image);
            milestoneEventImageService.addResource(milestoneEventImageDTO);
        }
        return ResponseView.success("", "添加成功");
    }
}
