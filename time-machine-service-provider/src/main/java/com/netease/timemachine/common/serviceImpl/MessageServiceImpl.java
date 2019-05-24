package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.MessageDao;
import com.netease.timemachine.common.dto.MessageDTO;
import com.netease.timemachine.common.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Resource
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

    @Override
    public boolean isExistMessage(Long sendId, Long receiverId, Long groupId, Integer groupType) {
        return messageDao.isExistMessage(sendId, receiverId, groupId, groupType);
    }
}
