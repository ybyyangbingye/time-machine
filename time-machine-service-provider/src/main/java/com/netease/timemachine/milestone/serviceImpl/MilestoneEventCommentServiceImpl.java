package com.netease.timemachine.milestone.serviceImpl;

import com.netease.timemachine.milestone.dao.MilestoneEventCommentDao;
import com.netease.timemachine.milestone.service.MilestoneEventCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午7:50
 */
@Service
public class MilestoneEventCommentServiceImpl implements MilestoneEventCommentService{

    @Autowired
    private MilestoneEventCommentDao milestoneEventCommentDao;

    @Override
    public int getCommentCountByGroupId(long groupId) {
        return milestoneEventCommentDao.getCommentCountByGroupId(groupId);
    }
}
