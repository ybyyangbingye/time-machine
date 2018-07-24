package com.netease.timemachine.milestone.service;

import com.netease.timemachine.milestone.dto.MilestoneDTO;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:20
 */
public interface MilestoneService {

    boolean addMilestone(MilestoneDTO milestoneDTO);

    List<MilestoneDTO> getMilestoneList(long childId);
}
