package com.netease.timemachine.mine.controller;

import com.google.common.collect.Lists;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.common.service.ResourceService;
import com.netease.timemachine.define.GroupTypeEnum;
import com.netease.timemachine.milestone.controller.BaseController;
import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import com.netease.timemachine.milestone.service.MilestoneEventLoverService;
import com.netease.timemachine.milestone.service.MilestoneEventService;
import com.netease.timemachine.milestone.vo.ResponseResult;
import com.netease.timemachine.mine.vo.MyResourceVO;
import com.netease.timemachine.moment.dto.MomentDTO;
import com.netease.timemachine.milestone.service.MilestoneEventCommentService;
import com.netease.timemachine.moment.service.CommentService;
import com.netease.timemachine.moment.service.GivealikeService;
import com.netease.timemachine.moment.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午4:07
 */
@RestController
@RequestMapping(value = "my_resource")
public class MyResourceController extends BaseController {

    @Autowired
    private MilestoneEventService milestoneEventService;
    @Autowired
    private MomentService momentService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private MilestoneEventCommentService milestoneEventCommentService;
    @Autowired
    private MilestoneEventLoverService milestoneEventLoverService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private GivealikeService givealikeService;

    private static final int SIZE_PER_PAGE = 40;

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public ResponseResult getMyResource(HttpServletRequest request, @PathVariable("user_id") long userId, @RequestParam("currentPage") int currentPage) {
        ResultDelegate delegate = new ResultDelegate() {
            @Override
            public Object getResultObject() throws Exception {

                List<MyResourceVO> myResourceVOS = Lists.newArrayList();
                MyResourceVO myResourceVO = new MyResourceVO();
                List<Long> groupIds = Lists.newArrayList();
                // 获取里程碑里发表的资源信息
                Map<Long, MilestoneEventDTO> groupIdMilestoneEventMap = new HashMap<>();
                List<MilestoneEventDTO> milestoneEventDTOS = milestoneEventService.listMilestoneEventByUserId(userId);
                for(MilestoneEventDTO milestoneEventDTO: milestoneEventDTOS) {
                    groupIds.add(milestoneEventDTO.getId());
                    groupIdMilestoneEventMap.put(milestoneEventDTO.getId(), milestoneEventDTO);
                }
                List<ResourceDTO> milestoneResources = resourceService.listByGroupIdAndGroupType(groupIds, GroupTypeEnum.MILESTONE.getType());

                // 获取朋友圈中发表的资源信息
                Map<Long, MomentDTO> groupIdMomentMap = new HashMap<>();
                List<MomentDTO> momentDTOS = momentService.listMomentByUserId(userId);
                for(MomentDTO momentDTO: momentDTOS) {
                    groupIds.add(momentDTO.getMomentId());
                    groupIdMomentMap.put(momentDTO.getMomentId(), momentDTO);
                }
                List<ResourceDTO> momentResourceList =  resourceService.listByGroupIdAndGroupType(groupIds, GroupTypeEnum.FRIEND_CIRCLE.getType());

                milestoneResources.addAll(momentResourceList);
                // 对结果进行分页返回
                int start = SIZE_PER_PAGE * (currentPage -1 );
                int end = SIZE_PER_PAGE * currentPage;
                List<ResourceDTO> resultResources = Lists.newArrayList();
                if(milestoneResources.size() > end) {
                    resultResources = milestoneResources.subList(start, end);
                }else if(milestoneResources.size() < end && milestoneResources.size() > start){
                    resultResources = milestoneResources.subList(start, milestoneResources.size());
                }

                MilestoneEventDTO milestoneEventDTO;
                MomentDTO momentDTO;
                for(ResourceDTO resourceDTO: resultResources) {
                    myResourceVO = new MyResourceVO();
                    myResourceVO.setResourceObj(resourceDTO.getResourceObj());
                    myResourceVO.setGmtCreate(resourceDTO.getGmtCreate());
                    myResourceVO.setGroupId(resourceDTO.getGroupId());

                    int groupType = resourceDTO.getGroupType();
                    int commentCount;
                    int loverCount;
                    if(groupType == GroupTypeEnum.MILESTONE.getType()) {
                        milestoneEventDTO = groupIdMilestoneEventMap.get(resourceDTO.getGroupId());
                        myResourceVO.setContent(milestoneEventDTO.getContent());
                        commentCount = milestoneEventCommentService.getCommentCountByGroupId(resourceDTO.getGroupId());
                        myResourceVO.setCommentCount(commentCount);
                        loverCount = milestoneEventLoverService.getLoverCountByGroupId(resourceDTO.getGroupId());
                        myResourceVO.setLoverCount(loverCount);
                    }else if(groupType == GroupTypeEnum.FRIEND_CIRCLE.getType()) {
                        momentDTO = groupIdMomentMap.get(resourceDTO.getGroupId());
                        myResourceVO.setContent(momentDTO.getDescription());
                        commentCount = commentService.getCommentCountByCommentId(resourceDTO.getGroupId());
                        myResourceVO.setCommentCount(commentCount);
                        loverCount = givealikeService.getLoverCountByCommentId(resourceDTO.getGroupId());
                        myResourceVO.setLoverCount(loverCount);
                    }
                    myResourceVOS.add(myResourceVO);
                }

                return myResourceVOS;
            }
        };
        return getResponseResult(request, delegate);
    }

}
