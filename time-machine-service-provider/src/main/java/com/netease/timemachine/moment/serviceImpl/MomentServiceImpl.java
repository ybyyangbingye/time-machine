package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.MomentDao;
import com.netease.timemachine.moment.dto.MomentDTO;
import com.netease.timemachine.moment.meta.Moment;
import com.netease.timemachine.moment.service.MomentService;
import com.netease.timemachine.moment.util.MomentDtoToMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 11:03
 */

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private MomentDao momentDao;
    /**
     *
     * @param userId
     * @param childId
     * @param currentPage
     * @return
     */
    @Override
    public List<MomentDTO> getMoments(Long userId, Long childId, Long currentPage) {
        Long start = currentPage * 5;
        Long end = start + 5;
        List<Moment> res = momentDao.getMoments(userId, childId, start, end);
        return MomentDtoToMeta.metaToDtoList(res);
    }

    /**
     *
     * @param moment_id
     * @return
     */
    @Override
    public List<String> getMomentFiles(Long moment_id) {
        List<String> res = momentDao.getMomentFiles(moment_id);
        return res;
    }

    /**
     *
     * @param userId
     * @param childId
     * @param description
     * @param files
     * @return
     */
    @Override
    public boolean addMoment(Long userId, Long childId, String description, String location,
                       List<String> files) {

        return false;
    }
}
