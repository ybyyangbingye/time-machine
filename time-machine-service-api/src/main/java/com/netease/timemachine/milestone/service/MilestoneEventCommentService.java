package com.netease.timemachine.milestone.service;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午7:36
 */
public interface MilestoneEventCommentService {

    /**
     * 根据动态id查询评论数量
     * @param groupId
     * @return
     */
    int getCommentCountByGroupId(long groupId);
}
