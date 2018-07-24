package com.netease.timemachine.milestone.service;

import com.netease.timemachine.milestone.dto.MilestoneEventDTO;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午5:41
 */
public interface MilestoneEventService {

    boolean addMilestoneEvent(MilestoneEventDTO milestoneEventDTO);

    MilestoneEventDTO getMilestoneEventByMilestoneId(long milestoneId);

    boolean deleteMilestoneEventById(long milestoneEventId);

}
