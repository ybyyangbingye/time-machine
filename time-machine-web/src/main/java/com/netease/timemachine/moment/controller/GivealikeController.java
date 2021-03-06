package com.netease.timemachine.moment.controller;

import com.netease.timemachine.common.dto.MessageDTO;
import com.netease.timemachine.common.service.MessageService;
import com.netease.timemachine.moment.dto.GivealikeDTO;
import com.netease.timemachine.moment.service.GivealikeService;
import com.netease.timemachine.moment.vo.GivealikeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/25 21:59
 */
@RestController
@RequestMapping("/like")
public class GivealikeController {

    @Autowired
    GivealikeService givealikeService;

    @Autowired
    MessageService messageService;

    @RequestMapping("/givealike")
    public GivealikeVO getGivealikeVO(HttpServletRequest request, @RequestBody GivealikeDTO givealikeDTO) {
        String nickname = givealikeService.getNickname(givealikeDTO);
        Long likeId = givealikeService.addGivealike(givealikeDTO);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSenderId(givealikeDTO.getUserId());
        messageDTO.setReceiverId(givealikeService.getLikedUser(givealikeDTO.getGroupId()));
        messageDTO.setGroupType(2);
        messageDTO.setGroupId(likeId);
        messageDTO.setContent(nickname + "点赞了你的状态");
        messageService.addMessage(messageDTO);
        return new GivealikeVO(givealikeDTO.getUserId(), nickname);
    }

    @RequestMapping("/getAll")
    public List<GivealikeDTO> getGivealikeVO(@RequestParam Long groupId) {
        return givealikeService.getAll(groupId);
    }

    @RequestMapping("/deletealike")
    public GivealikeVO deletealike(HttpServletRequest request, @RequestBody GivealikeDTO givealikeDTO){
        givealikeService.deletealike(givealikeDTO);
        String nickname = givealikeService.getNickname(givealikeDTO);
        return new GivealikeVO(givealikeDTO.getUserId(), nickname);
    }

}
