package com.netease.timemachine.common.dao;

import com.netease.timemachine.common.dto.MessageDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageDao {

    @Insert("insert into message(sender_id,receiver_id,group_type,group_id,content) values(#{senderId},#{receiverId},#{groupType},#{groupId},#{content})")
    void addMessage(MessageDTO messageDTO);

    @Select("select * from message where user_id=#{userId}")
    List<MessageDTO> getMessages(Long userId);

    @Delete("delete from message where message_id=#{messageId}")
    void deleteMessage(Long messageId);
}
