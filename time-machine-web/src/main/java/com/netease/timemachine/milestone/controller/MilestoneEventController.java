package com.netease.timemachine.milestone.controller;

import com.google.common.collect.Lists;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.common.dto.LabelBelongedDTO;
import com.netease.timemachine.common.dto.LabelDTO;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.common.dto.UserRemindedDTO;
import com.netease.timemachine.common.service.LabelBelongedService;
import com.netease.timemachine.common.service.LabelService;
import com.netease.timemachine.common.service.ResourceService;
import com.netease.timemachine.common.service.UserRemindedService;
import com.netease.timemachine.define.GroupTypeEnum;
import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import com.netease.timemachine.milestone.service.MilestoneEventService;
import com.netease.timemachine.milestone.vo.MilestoneEventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
    @Autowired
    private UserRemindedService userRemindedService;
    @Autowired
    private LabelBelongedService labelBelongedService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private UserService userService;

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

        // 添加被提醒人员
        List<UserDTO> remindedUsers = milestoneEventVO.getRemindedUsers();
        UserRemindedDTO userRemindedDTO = new UserRemindedDTO();
        userRemindedDTO.setGroupId(milestoneEventDTO.getId());
        userRemindedDTO.setGroupType(GroupTypeEnum.MILESTONE.getType());
        userRemindedDTO.setGmtCreate(new Date());
        for(UserDTO user: remindedUsers) {
            userRemindedDTO.setUserId(user.getUserId());
            userRemindedService.addUserReminded(userRemindedDTO);
        }

        // 添加标签
        List<LabelDTO> labelDTOS = milestoneEventVO.getLabels();
        LabelBelongedDTO labelBelongedDTO = new LabelBelongedDTO();
        labelBelongedDTO.setGroupId(milestoneEventDTO.getId());
        labelBelongedDTO.setGroupType(GroupTypeEnum.MILESTONE.getType());
        labelBelongedDTO.setGmtCreate(new Date());
        for(LabelDTO labelDTO: labelDTOS) {
            labelBelongedDTO.setLabelId(labelDTO.getId());
            labelBelongedService.addLabelBelonged(labelBelongedDTO);
        }

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
        if(milestoneEventDTO == null) {
            return ResponseView.fail(400, "参数有误!");
        }
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

        // 获取被提醒人信息 如果性能不好，可对数据库做冗余处理
        List<UserRemindedDTO> userRemindedDTOS = userRemindedService.getByGroupTypeAndGroupId(GroupTypeEnum.MILESTONE.getType(), milestoneEventDTO.getId());
        List<Long> userIds = Lists.newArrayList();
        for(UserRemindedDTO userRemindedDTO: userRemindedDTOS) {
            userIds.add(userRemindedDTO.getId());
        }
        if(!CollectionUtils.isEmpty(userIds)) {
            List<UserDTO> users = userService.listUsersByIds(userIds);
            milestoneEventVO.setRemindedUsers(users);
        }

        // 获取标签
        List<LabelBelongedDTO> labelBelongedDTOS = labelBelongedService.getByGroupTypeAndGroupId(GroupTypeEnum.MILESTONE.getType(), milestoneEventDTO.getId());
        List<Long> labelIds = Lists.newArrayList();
        for(LabelBelongedDTO belongedDTO: labelBelongedDTOS) {
            labelIds.add(belongedDTO.getId());
        }
        if(!CollectionUtils.isEmpty(labelIds)) {
            List<LabelDTO> labelDTOS = labelService.getLabelsByIds(labelIds);
            milestoneEventVO.setLabels(labelDTOS);
        }

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
        // 删除被提醒人
        userRemindedService.deleteUserRemindedByGroupTypeAndGroupId(GroupTypeEnum.MILESTONE.getType(), milestoneEventId);
        // 删除标签
        labelBelongedService.deleteByGroupTypeAndGroupId(GroupTypeEnum.MILESTONE.getType(), milestoneEventId);

        return ResponseView.success("", "删除成功");
    }
}
