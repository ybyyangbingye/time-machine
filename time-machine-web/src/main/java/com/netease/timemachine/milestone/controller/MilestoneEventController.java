package com.netease.timemachine.milestone.controller;

import com.google.common.collect.Lists;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.common.service.ResourceService;
import com.netease.timemachine.define.GroupTypeEnum;
import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import com.netease.timemachine.milestone.service.MilestoneEventService;
import com.netease.timemachine.milestone.vo.MilestoneEventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加里程碑事件
     * @param request
     * @param milestoneEventVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity addMilestoneEvent(HttpServletRequest request, @RequestBody MilestoneEventVO milestoneEventVO) {
        // 添加事件到主表
        MilestoneEventDTO milestoneEventDTO = new MilestoneEventDTO();
        milestoneEventDTO.setMilestoneId(milestoneEventVO.getMilestoneId());
        // TODO: 位置信息格式需要和前端确认
        milestoneEventDTO.setLocation(milestoneEventVO.getLocation());
        milestoneEventDTO.setTime(milestoneEventVO.getTime());
        milestoneEventDTO.setGmtCreate(new Date());
        milestoneEventService.addMilestoneEvent(milestoneEventDTO);

        // 添加事件关联的图片
        ResourceDTO milestoneEventImageDTO = new ResourceDTO();
        milestoneEventImageDTO.setResourceType(milestoneEventVO.getResourceType());
        milestoneEventImageDTO.setGroupId(milestoneEventDTO.getId());
        milestoneEventImageDTO.setGroupType(GroupTypeEnum.MILESTONE.getType());
        milestoneEventImageDTO.setGmtCreate(new Date());
        List<String> resources = milestoneEventVO.getResources();
        for(String resource: resources) {
            milestoneEventImageDTO.setResourceObj(resource);
            milestoneEventImageService.addResource(milestoneEventImageDTO);
        }

        // TODO : 添加被提醒人和标签
        return ResponseView.success("", "添加成功");
    }

    /**
     * 获取里程碑事件
     * @param request
     * @param milestoneId
     * @return
     */
    @RequestMapping(value = "/{milestone_id}", method = RequestMethod.GET)
    public ResponseEntity getMilestoneEvent(HttpServletRequest request, @PathVariable("milestone_id") long milestoneId) {
        MilestoneEventVO milestoneEventVO = new MilestoneEventVO();

        MilestoneEventDTO milestoneEventDTO = milestoneEventService.getMilestoneEventByMilestoneId(milestoneId);
        milestoneEventVO.setId(milestoneEventDTO.getId());
        milestoneEventVO.setMilestoneId(milestoneEventDTO.getMilestoneId());
        milestoneEventVO.setLocation(milestoneEventDTO.getLocation());
        milestoneEventVO.setTime(milestoneEventDTO.getTime());
        milestoneEventVO.setGmtCreate(milestoneEventDTO.getGmtCreate());
        milestoneEventVO.setGmtModified(milestoneEventDTO.getGmtModified());

        //获取图片、视频信息
        List<String> resources = Lists.newArrayList();
        List<ResourceDTO> resourceDTOS = milestoneEventImageService.getResourceByGroupIdAndGroupType(milestoneEventVO.getId(), GroupTypeEnum.MILESTONE.getType());
        for(ResourceDTO resourceDTO: resourceDTOS) {
            resources.add(resourceDTO.getResourceObj());
            milestoneEventVO.setResourceType(resourceDTO.getResourceType());
        }
        milestoneEventVO.setResources(resources);
        // TODO: 获取被提醒人信息和标签
        return ResponseView.success(milestoneEventVO);
    }

    /**
     * 根据里程碑中事件id删除事件及其资源
     *
     * @param request
     * @param milestoneEventId
     * @return
     */
    @RequestMapping(value = "/{milestone_event_id}", method = RequestMethod.DELETE)
    @Transactional
    public ResponseEntity deleteMilestoneEvent(HttpServletRequest request, @PathVariable("milestone_event_id") long milestoneEventId) {
        // 删除主表
        milestoneEventService.deleteMilestoneEventById(milestoneEventId);
        // 删除资源表
        milestoneEventImageService.deleteResourceByGroupIdAndGroupType(milestoneEventId, GroupTypeEnum.MILESTONE.getType());

        // TODO: 删除标签和被提醒人列表

        return ResponseView.success("", "删除成功");
    }
}
