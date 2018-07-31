package com.netease.timemachine.milestone.serviceImpl;

import com.netease.timemachine.milestone.dao.MilestoneEventLoverDao;
import com.netease.timemachine.milestone.service.MilestoneEventLoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午8:19
 */
@Service
public class MilestoneEventLoverServiceImpl implements MilestoneEventLoverService{

    @Autowired
    private MilestoneEventLoverDao milestoneEventLoverDao;

    @Override
    public int getLoverCountByGroupId(long groupId) {
        return milestoneEventLoverDao.getLoverCountByGroupId(groupId);
    }
}
