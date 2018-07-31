package com.netease.timemachine.moment.service;

import com.netease.timemachine.moment.dto.CommentDTO;

import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:14 2018/7/24
 **/
public interface CommentService {

    /**
     * 添加一条评论
     * @param commentDTO
     */
    Long insertComment(CommentDTO commentDTO);

    /**
     * 获取该条状态下的所有评论
     * @param childId
     * @param momentId
     * @return
     */
    List<CommentDTO> selectComments(Long childId,Long momentId);

    /**
     * 删除某条状态下的某个评论
     * @param commentId
     */
    void deleteComment(Long commentId);

    /**
     * 根据动态id获取动态的评论数量
     * @param momentId
     * @return
     */
    int getCommentCountByMomentId(long momentId);

    /*
     * 通过commentId查询一条评论信息
     * @param commentId
     * @return
     */
    CommentDTO selectByCommentId(Long commentId);
}
