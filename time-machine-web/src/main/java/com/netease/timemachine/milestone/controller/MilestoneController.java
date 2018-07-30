package com.netease.timemachine.milestone.controller;

import com.google.common.collect.Lists;
import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.common.service.ResourceService;
import com.netease.timemachine.milestone.dto.MilestoneDTO;
import com.netease.timemachine.milestone.service.MilestoneService;
import com.netease.timemachine.milestone.vo.MilestoneVO;
import com.netease.timemachine.milestone.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
public class MilestoneController extends BaseController{

    @Autowired
    private MilestoneService milestoneService;
    @Autowired
    private ChildService childService;
    @Autowired
    private ResourceService resourceService;

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
    @Cacheable(value = "milestone", key = "#childId")
    public ResponseResult getMilestoneList(HttpServletRequest request, @PathVariable("child_id") long childId) {
        ResultDelegate delegate = new ResultDelegate() {
            @Override
            public Object getResultObject() throws Exception {
                ChildDTO childDTO = childService.selectChildById(childId);
                List<MilestoneDTO> milestoneDTOList = milestoneService.getMilestoneList(childId);
                List<MilestoneVO> milestoneVOS = Lists.newArrayList();
                MilestoneVO milestoneVO;
                String coverObj;
                ResourceDTO resourceDTO;
                for(MilestoneDTO milestoneDTO: milestoneDTOList) {
                    milestoneVO = new MilestoneVO(milestoneDTO);
                    Date nowDate = milestoneDTO.getTime();
                    int age = nowDate.getYear() - childDTO.getBirthDate().getYear();
                    milestoneVO.setChildAge(age);
                    resourceDTO = resourceService.getResourceByMilestoneId(milestoneDTO.getId());
                    if(resourceDTO != null) {
                        coverObj = resourceDTO.getResourceObj();
                        milestoneVO.setCoverObj(coverObj);
                    }
                    milestoneVOS.add(milestoneVO);
                }
                return milestoneVOS;
            }
        };

        return getResponseResult(request, delegate);
    }
}
