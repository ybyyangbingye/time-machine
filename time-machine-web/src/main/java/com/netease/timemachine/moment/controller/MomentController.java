package com.netease.timemachine.moment.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.util.ChildBirthDay;
import com.netease.timemachine.account.util.ResponseView;
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
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private MomentService momentService;

    @Autowired
    private ChildService childService;

    @Autowired
    private GivealikeService givealikeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LabelService labelService;

    /**
     *
     * @param userId
     * @param childId
     * @param currentPage
     * @param type
     * @return
     */
    @RequestMapping(value = "/getRecords", method = RequestMethod.POST)
    public ResponseEntity getMoments(@RequestParam Long userId, @RequestParam Long childId,
                                  @RequestParam Long currentPage, @RequestParam Long type) {
        List<MomentVO> moments = MomentVoToDto.dtoListToVoList(momentService.getMoments(childId, currentPage, type));
        List<MomentVO> res = new ArrayList<>();
        Date date = childService.selectChildById(childId).getBirthDate();
        Integer months = ChildBirthDay.getChildMonths(date);
        for(MomentVO moment : moments) {
            moment.setFiles(momentService.getMomentFiles(moment.getMomentId()));
            moment.setLabels(momentService.getMomentLabels(moment.getMomentId()));
            moment.setChildAge(ChildBirthDay.getAge(date));
            moment.setNickName(momentService.getNickName(childId,userId));
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
     *
     * @param momentVO
     */
    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    public ResponseEntity addMoment(@RequestBody MomentVO momentVO) {
        Long momentId = momentService.addMoment(MomentVoToDto.voToDto(momentVO),momentVO.getFiles());
        labelService.addLabels(momentVO.getCreatorId(), momentVO.getChildId(),
                momentId, momentVO.getLabels());
        return ResponseView.success(Boolean.TRUE,"添加成功");
    }

    /**
     *
     * @param momentId
     */
    @RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
    public ResponseEntity deleteMoment(@RequestParam Long momentId) {
        momentService.deleteMoment(momentId);
        return ResponseView.success(Boolean.TRUE,"删除成功");
    }

}
