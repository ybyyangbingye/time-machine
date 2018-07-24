package com.netease.timemachine.milestone.serviceImpl;

import com.netease.timemachine.milestone.dao.MilestoneEventDao;
import com.netease.timemachine.milestone.dto.MilestoneEventDTO;
import com.netease.timemachine.milestone.service.MilestoneEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午7:06
 */
@Service
public class MilestoneEventServiceImpl implements MilestoneEventService{


    @Autowired
    private MilestoneEventDao milestoneEventDao;

    /**
     * 添加里程碑事件
     * @param milestoneEventDTO
     * @return
     */
    @Override
    public boolean addMilestoneEvent(MilestoneEventDTO milestoneEventDTO) {
        return milestoneEventDao.addMilestoneEvent(milestoneEventDTO);
    }

    /**
     * 根据里程碑id获取里程碑事件
     * @param milestoneId
     * @return
     */
    @Override
    public MilestoneEventDTO getMilestoneEventByMilestoneId(long milestoneId) {
        return milestoneEventDao.getMilestoneEventByMilestoneId(milestoneId);
    }

    @Override
    public MilestoneEventDTO getMilestoneEventById(long id) {
        return milestoneEventDao.getMilestoneEventById(id);
    }

    @Override
    public boolean modifyMilestoneEventById(MilestoneEventDTO milestoneEventDTO) {
        return milestoneEventDao.modifyMilestoneEventById(milestoneEventDTO);
    }

    /**
     * 删除事件
     *
     * @param milestoneEventId
     * @return
     */
    @Override
    @Transactional
    public boolean deleteMilestoneEventById(long milestoneEventId) {
        return milestoneEventDao.deleteMilestoneEventById(milestoneEventId);
    }
}
