package com.netease.timemachine.moment.dao;

import com.netease.timemachine.moment.meta.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 23:12 2018/7/24
 **/
@Mapper
public interface CommentDao {
    /**
     * 添加一条评论
     * @param comment
     */
    @Insert("insert into comment (moment_id,content,reply_id,parent_id,create_time) values (#{momentId}," +
            "#{content},#{replyId},#{parentId},#{createTime})")
    void insertComment(Comment comment);

    /**
     * 获取该条状态下的所有评论，根据时间排序
     * @param momentId
     * @return
     */
    @Select("select * from comment where moment_id = #{momentId} order by create_time")
    List<Comment> selectComments(Long momentId);

    /**
     * 删除某条状态下的某个评论
     */
    @Delete("delete from comment where comment_id = #{commentId}")
    void deleteComment(Long commentId);

}
