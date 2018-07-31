package com.netease.timemachine.milestone.service;

import com.netease.timemachine.milestone.dto.MilestoneEventDTO;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午5:41
 */
public interface MilestoneEventService {

    boolean addMilestoneEvent(MilestoneEventDTO milestoneEventDTO);

    /**
     * 根据里程碑获取事件，目前每个里程碑下面最多只能有一个事件
     * @param milestoneId
     * @return
     */
    MilestoneEventDTO getMilestoneEventByMilestoneId(long milestoneId);

    /**
     * 根据里程碑事件id获取事件对象
     * @param id
     * @return
     */
    MilestoneEventDTO getMilestoneEventById(long id);

    /**
     * 根据id修改事件
     * @param milestoneEventDTO
     * @return
     */
    boolean modifyMilestoneEventById(MilestoneEventDTO milestoneEventDTO);

    boolean deleteMilestoneEventById(long milestoneEventId);

    /**
     * 根据用户id获取其创建的里程碑
     * @param userId
     * @return
     */
    List<MilestoneEventDTO> listMilestoneEventByUserId(long userId);

}
