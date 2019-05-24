package com.netease.timemachine.moment.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.util.ChildBirthDay;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.common.dto.MessageDTO;
import com.netease.timemachine.common.service.MessageService;
import com.netease.timemachine.moment.service.LabelService;
import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.service.CommentService;
import com.netease.timemachine.moment.service.GivealikeService;
import com.netease.timemachine.moment.service.MomentService;
import com.netease.timemachine.moment.util.CommentVoToDto;
import com.netease.timemachine.moment.util.MomentVoToDto;
import com.netease.timemachine.moment.vo.MomentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 11:18
 */

@RestController
@RequestMapping("/records")
public class MomentController {

    @Resource
    private MomentService momentService;

    @Resource
    private ChildService childService;

    @Resource
    private GivealikeService givealikeService;

    @Resource
    private CommentService commentService;

    @Resource
    private LabelService labelService;

    @Resource
    private MessageService messageService;

    /**
     *
     * @param userId
     * @param childId
     * @param currentPage
     * @param groupType
     * @return
     */
    @RequestMapping(value = "/getRecords", method = RequestMethod.POST)
    public ResponseEntity getMoments(@RequestParam Long userId, @RequestParam Long childId,
                                  @RequestParam Long currentPage, @RequestParam Long groupType) {
        List<MomentVO> moments = MomentVoToDto.dtoListToVoList(momentService.getMoments(childId, currentPage, groupType));
        List<MomentVO> res = new ArrayList<>();
        Date date = childService.selectChildById(childId).getBirthDate();
        Integer months = ChildBirthDay.getChildMonths(date);
        for(MomentVO moment : moments) {
            moment.setFiles(momentService.getMomentFiles(moment.getMomentId()));
            List<Integer> types = momentService.getType(moment.getMomentId());
            if(!CollectionUtils.isEmpty(types)) {
                if(types.size() == 1 && types.get(0) == 2) {
                    moment.setResourceType(2);
                }
                else {
                    moment.setResourceType(1);
                }
            }
            moment.setLabels(momentService.getMomentLabels(moment.getMomentId()));
            moment.setChildAge(ChildBirthDay.getAge(date));
            moment.setNickName(momentService.getNickName(childId,moment.getCreatorId()));
            List<CommentDTO> comments = commentService.selectComments(childId, moment.getMomentId());
            moment.setComments(CommentVoToDto.commentDtoToVoList(comments));
            moment.setGiveALike(givealikeService.getAll(moment.getMomentId()));
            moment.setHasLike(givealikeService.isGivealike(userId,moment.getMomentId()));
            res.add(moment);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("moments",res);
        jsonObject.put("months",months);
        return ResponseView.success(jsonObject,"查询成功");

    }

    /**
     * 添加状态、里程碑
     * @param momentVO
     */
    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    public ResponseEntity addMoment(@RequestBody MomentVO momentVO) {
        Long momentId = momentService.addMoment(MomentVoToDto.voToDto(momentVO),momentVO.getFiles());
        labelService.addLabels(momentVO.getCreatorId(), momentVO.getChildId(),
                momentId, momentVO.getLabels());

        //添加提醒
        List<Long> receivers = momentService.getReceivers(momentVO.getChildId());
        for(Long userId : receivers) {
            if(!userId.equals(momentVO.getCreatorId())) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setSenderId(momentVO.getCreatorId());
                messageDTO.setReceiverId(userId);
                messageDTO.setGroupType(1);
                messageDTO.setGroupId(momentId);
                String nickName = momentService.getNickName(momentVO.getChildId(),momentVO.getCreatorId());
                messageDTO.setContent(nickName + "发表了新的状态");
                messageService.addMessage(messageDTO);
            }
        }
        return ResponseView.success(Boolean.TRUE,"添加成功");
    }

    /**
     * 删除状态或里程碑
     * @param momentId
     */
    @RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
    public ResponseEntity deleteMoment(@RequestParam Long momentId) {
        momentService.deleteMoment(momentId);
        return ResponseView.success(Boolean.TRUE,"删除成功");
    }

}
