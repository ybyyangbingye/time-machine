package com.netease.timemachine.moment.controller;

import com.netease.timemachine.moment.dto.GivealikeDTO;
import com.netease.timemachine.moment.service.GivealikeService;
import com.netease.timemachine.moment.vo.GivealikeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/25 21:59
 */
@RestController
public class GivealikeController {

    @Autowired
    GivealikeService givealikeService;

    @RequestMapping("/givealike")
    public GivealikeVO getGivealikeVO(HttpServletRequest request, @RequestBody GivealikeDTO givealikeDTO) {
        String nickname = givealikeService.getNickname(givealikeDTO);
        givealikeService.addGivealike(givealikeDTO);
        return new GivealikeVO(givealikeDTO.getUserId(), nickname);
    }

    @RequestMapping("/deletealike")
    public void deletealike(HttpServletRequest request, @RequestBody GivealikeDTO givealikeDTO){
        givealikeService.deletealike(givealikeDTO);
    }

}
