package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.MessageDao;
import com.netease.timemachine.common.dto.MessageDTO;
import com.netease.timemachine.common.meta.Message;
import com.netease.timemachine.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDao messageDao;

    @Override
    public void addMessage(MessageDTO messageDTO) {
        messageDao.addMessage(messageDTO);
    }

    @Override
    public List<MessageDTO> getMessages(Long userId) {
        return messageDao.getMessages(userId);
    }

    @Override
    public void deleteMessage(Long messageId) {
        messageDao.deleteMessage(messageId);
    }


}
