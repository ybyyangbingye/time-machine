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
     * 获取该条状态或者里程牌下的所有评论
     * @param childId
     * @param groupId
     * @param groupType
     * @return
     */
    List<CommentDTO> selectComments(Long childId,Long groupId,Integer groupType);

    /**
     * 删除某条状态或者里程牌下的某个评论
     * @param commentId
     * @param groupType
     */
    void deleteComment(Long commentId,Integer groupType);

    /**
     * 根据动态id获取状态或者里程牌的评论数量
     * @param groupId
     * @param groupType
     * @return
     */
    int getCommentCountByGroupIdType(Long groupId,Integer groupType);

    /**
     * 通过commentId查询一条评论信息
     * @param commentId
     * @param groupType
     * @return
     */
    CommentDTO selectByCommentIdType(Long commentId,Integer groupType);
}
