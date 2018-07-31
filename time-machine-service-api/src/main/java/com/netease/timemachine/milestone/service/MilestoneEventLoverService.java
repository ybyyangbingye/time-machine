package com.netease.timemachine.milestone.service;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午8:12
 */
public interface MilestoneEventLoverService {
    /**
     * 根据动态id获取所有的喜欢该动态的人的数量
     * @param groupId
     * @return
     */
    int getLoverCountByGroupId(long groupId);
}
