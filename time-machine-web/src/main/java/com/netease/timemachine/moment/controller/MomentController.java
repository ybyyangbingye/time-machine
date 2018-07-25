package com.netease.timemachine.moment.controller;

import com.netease.timemachine.moment.service.MomentService;
import com.netease.timemachine.moment.util.LabelVoToDto;
import com.netease.timemachine.moment.util.MomentVoToDto;
import com.netease.timemachine.moment.vo.MomentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        momentService.addMoment(MomentVoToDto.voToDto(momentVO),
                momentVO.getFiles(),
                LabelVoToDto.voListToDtoList(momentVO.getLabels()));
    }
}
