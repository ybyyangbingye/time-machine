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
    @Insert("insert into comment (moment_id,content,reply_id,parent_id) values (#{momentId}," +
            "#{content},#{replyId},#{parentId})")
    @Options(useGeneratedKeys=true, keyProperty="commentId", keyColumn="comment_id")
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

    /**
     * 根据动态id获取评论数量
     * @param momentId
     * @return
     */
    @Select("select count(*) from comment where moment_id = #{momentId}")
    int getCommentCountByMomentId(long momentId);

    /*
     * 通过commentId查询一条评论信息
     * @param commentId
     * @return
     */
    @Select("select * from comment where comment_id = #{commentId}")
    Comment selectByCommentId(Long commentId);
}
