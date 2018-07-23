package com.netease.timemachine.milestone.serviceImpl;

import com.netease.timemachine.milestone.dao.MilestoneDao;
import com.netease.timemachine.milestone.dto.MilestoneDTO;
import com.netease.timemachine.milestone.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:33
 */
@Service
public class MilestoneServiceImpl implements MilestoneService{

    @Autowired
    private MilestoneDao milestoneDao;

    @Override
    public boolean addMilestone(MilestoneDTO milestoneDTO) {
        milestoneDTO.setGmtCreate(new Date());
        return milestoneDao.addMilestone(milestoneDTO);
    }
}
