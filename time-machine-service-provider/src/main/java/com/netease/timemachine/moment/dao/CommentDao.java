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
    @Insert("insert into comment (group_id,group_type,content,reply_id,parent_id) values (#{groupId}," +
            "#{groupType},#{content},#{replyId},#{parentId})")
    @Options(useGeneratedKeys=true, keyProperty="commentId", keyColumn="comment_id")
    void insertComment(Comment comment);

    /**
     * 获取下状态或者里程牌的所有评论，根据时间排序
     * @param groupId
     * @param groupType 状态：1，里程牌2
     * @return
     */
    @Select("select * from comment where group_id = #{groupId} and group_type=#{groupType} order by create_time")
    List<Comment> selectComments(@Param("groupId") Long groupId, @Param("groupType") Integer groupType);

    /**
     * 删除某条状态或者里程牌下的某个评论
     * @param commentId
     * @param groupType 状态：1，里程牌2
     */
    @Delete("delete from comment where comment_id = #{commentId} and group_type=#{groupType}")
    void deleteComment(@Param("commentId") Long commentId, @Param("groupType") Integer groupType);

    /**
     * 根据动态id获取评论数量
     * @param groupId
     * @param groupType 状态：1，里程牌2
     * @return
     */
    @Select("select count(*) from comment where group_id = #{groupId} and group_type=#{groupType}")
    int getCommentCountByGroupIdType(@Param("groupId") Long groupId, @Param("groupType") Integer groupType);

    /**
     * 通过commentId查询一条评论信息
     * @param commentId
     * @param groupType 状态：1，里程牌2
     * @return
     */
    @Select("select * from comment where comment_id = #{commentId} and group_type=#{groupType}")
    Comment selectByCommentIdType(@Param("commentId") Long commentId, @Param("groupType") Integer groupType);
}
