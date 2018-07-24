package com.netease.timemachine.milestone.serviceImpl;

import com.netease.timemachine.milestone.dao.MilestoneDao;
import com.netease.timemachine.milestone.dto.MilestoneDTO;
import com.netease.timemachine.milestone.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:33
 */
@Service
public class MilestoneServiceImpl implements MilestoneService{

    @Autowired
    private MilestoneDao milestoneDao;

    /**
     * 添加里程碑
     * @param milestoneDTO
     * @return
     */
    @Override
    public boolean addMilestone(MilestoneDTO milestoneDTO) {
        milestoneDTO.setGmtCreate(new Date());
        return milestoneDao.addMilestone(milestoneDTO);
    }

    /**
     * 获取里程碑列表
     * @param childId
     * @return
     */
    @Override
    public List<MilestoneDTO> getMilestoneList(long childId) {
        return  milestoneDao.getMilestoneList(childId);
    }


}
