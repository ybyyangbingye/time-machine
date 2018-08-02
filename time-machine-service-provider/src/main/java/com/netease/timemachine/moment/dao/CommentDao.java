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
    @Insert("insert into comment (group_id,content,reply_id,parent_id) values (#{groupId}," +
            "#{content},#{replyId},#{parentId})")
    @Options(useGeneratedKeys=true, keyProperty="commentId", keyColumn="comment_id")
    void insertComment(Comment comment);

    /**
     * 获取下状态或者里程牌的所有评论，根据时间排序
     * @param groupId
     * @return
     */
    @Select("select * from comment where group_id = #{groupId} order by create_time")
    List<Comment> selectComments(@Param("groupId") Long groupId);

    /**
     * 删除某条状态或者里程牌下的某个评论
     * @param commentId
     */
    @Delete("delete from comment where comment_id = #{commentId}")
    void deleteComment(@Param("commentId") Long commentId);

    /**
     * 根据动态id获取评论数量
     * @param groupId
     * @return
     */
    @Select("select count(*) from comment where group_id = #{groupId}")
    int getCommentCountByGroupIdType(@Param("groupId") Long groupId);

    /**
     * 通过commentId查询一条评论信息
     * @param commentId
     * @return
     */
    @Select("select * from comment where comment_id = #{commentId}")
    Comment selectByCommentIdType(@Param("commentId") Long commentId);
}
