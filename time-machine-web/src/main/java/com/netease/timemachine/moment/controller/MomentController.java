package com.netease.timemachine.moment.controller;

import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.util.ChildBirthDay;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.common.service.LabelService;
import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.meta.Givealike;
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
@RequestMapping("/moment")
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
     * @return
     */
    @RequestMapping(value = "/getMoments", method = RequestMethod.POST)
    public List<MomentVO> getMoments(@RequestParam Long userId,
                                  @RequestParam Long childId,
                                  @RequestParam Long currentPage) {
        List<MomentVO> moments = MomentVoToDto.dtoListToVoList(momentService.getMoments(userId, childId, currentPage));
        List<MomentVO> res = new ArrayList<>();
        for(MomentVO moment : moments) {
            moment.setFiles(momentService.getMomentFiles(moment.getMomentId()));
            moment.setLabels(momentService.getMomentLabels(moment.getMomentId()));
            Date date = childService.selectChildById(childId).getBirthDate();
            moment.setChildAge(ChildBirthDay.getAge(date));
            moment.setNickName(momentService.getNickName(childId,userId));
            List<CommentDTO> comments = commentService.selectComments(moment.getMomentId());
            moment.setComments(CommentVoToDto.commentDtoToVoList(comments));
            moment.setGiveALike(givealikeService.getAll(moment.getMomentId()));
            res.add(moment);
        }
        return res;
    }

    /**
     *
     * @param momentVO
     */
    @RequestMapping(value = "/addMoment", method = RequestMethod.POST)
    public void addMoment(@RequestBody MomentVO momentVO) {
//        momentService.addMoment(MomentVoToDto.voToDto(momentVO),
//                momentVO.getFiles(),
//                LabelVoToDto.voListToDtoList(momentVO.getLabels()));
        Long momentId = momentService.addMoment(MomentVoToDto.voToDto(momentVO),momentVO.getFiles());
        labelService.addLabels(momentVO.getCreatorId(), momentVO.getChildId(),
                momentId, momentVO.getLabels());
    }

    /**
     *
     * @param momentId
     */
    @RequestMapping(value = "/deleteMoment", method = RequestMethod.POST)
    public void deleteMoment(@RequestParam Long momentId) {
        momentService.deleteMoment(momentId);
    }

    @RequestMapping(value = "/incrementViews", method = RequestMethod.POST)
    public ResponseEntity incrementViews(@RequestParam String resourceObj){
        momentService.incrementViews(resourceObj);
        return ResponseView.success(null, "浏览量更新成功");
    }
}
