package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.MessageDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MessageDao {

    @Insert("insert into message(sender_id,receiver_id,group_type,group_id,content) values(#{senderId},#{receiverId},#{groupType},#{groupId},#{content})")
    void addMessage(MessageDTO messageDTO);

    @Select("select * from message where user_id=#{userId}")
    List<MessageDTO> getMessages(Long userId);

    @Delete("delete from message where message_id=#{messageId}")
    void deleteMessage(Long messageId);

    /**
     * 查找该条消息是否存在
     * @param sendId
     * @param receiverId
     * @param groupId
     * @param groupType
     * @return
     */
    @Select("select count(*) from message where sender_id=#{sendId} and " +
            "receiver_id=#{receiverId} and group_id=#{groupId} and group_type=#{groupType}")
    boolean isExistMessage(@Param("sendId") Long sendId, @Param("receiverId") Long receiverId,
                           @Param("groupId") Long groupId, @Param("groupType") Integer groupType);
}
