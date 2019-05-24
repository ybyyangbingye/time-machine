package com.netease.timemachine.common.controller;

import com.netease.timemachine.common.dto.MessageDTO;
import com.netease.timemachine.common.service.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/getMessages", method = RequestMethod.POST)
    public List<MessageDTO> getMessages(@RequestParam Long userId) {
        return messageService.getMessages(userId);
    }

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
    public void deleteMessage(@RequestParam Long messageId) {
        messageService.deleteMessage(messageId);
    }
}
