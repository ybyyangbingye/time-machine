package com.netease.timemachine.momment.serviceImpl;

import com.netease.timemachine.momment.dao.MommentDao;
import com.netease.timemachine.momment.dto.MommentDTO;
import com.netease.timemachine.momment.meta.Momment;
import com.netease.timemachine.momment.service.MommentService;
import com.netease.timemachine.momment.util.MommentDtoToMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 11:03
 */

@Service
public class MommentServiceImpl implements MommentService {

    @Autowired
    private MommentDao mommentDao;
    /**
     *
     * @param userId
     * @param childId
     * @param currentPage
     * @return
     */
    @Override
    public List<MommentDTO> getMomments(Long userId, Long childId, Long currentPage) {
        Long start = currentPage * 5;
        Long end = start + 5;
        List<Momment> res = mommentDao.getMomments(userId, childId, start, end);
        return MommentDtoToMeta.metaToDtoList(res);
    }

    @Override
    public List<String> getMommentFiles(Long comment_id) {
        List<String> res = mommentDao.getMommentFiles(comment_id);
        return res;
    }


}
