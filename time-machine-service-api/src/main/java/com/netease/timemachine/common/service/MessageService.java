package com.netease.timemachine.common.service;

import com.netease.timemachine.common.dto.MessageDTO;

import java.util.List;


public interface MessageService {

    /**
     * 添加提醒
     * @param messageDTO
     */
    void addMessage(MessageDTO messageDTO);

    /**
     * 获得提醒
     * @param userId
     * @return
     */
    List<MessageDTO> getMessages(Long userId);

    /**
     * 阅读后删除提醒
     * @param messageId
     */
    void deleteMessage(Long messageId);

    /**
     * 查找该条信息是否存在
     * @param sendId
     * @param receiverId
     * @param groupId
     * @return
     */
    boolean isExistMessage(Long sendId, Long receiverId, Long groupId, Integer groupType);
}
