package com.netease.timemachine.moment.controller;

import com.netease.timemachine.moment.service.MomentService;
import com.netease.timemachine.moment.util.MomentVoToDto;
import com.netease.timemachine.moment.vo.MomentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/getMoments", method = RequestMethod.POST)
    public List<MomentVO> getMoments(@RequestParam Long userId,
                                  @RequestParam Long childId,
                                  @RequestParam Long currentPage) {
        List<MomentVO> moments = MomentVoToDto.dtoListToVo(momentService.getMoments(userId, childId, currentPage));
        List<MomentVO> res = new ArrayList<>();
        for(MomentVO moment : moments) {
            moment.setFiles(momentService.getMomentFiles(moment.getMomentId()));
            res.add(moment);
        }
        return res;
    }
}
