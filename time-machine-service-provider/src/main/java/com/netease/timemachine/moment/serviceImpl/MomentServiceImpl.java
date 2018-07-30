package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.MomentDao;
import com.netease.timemachine.moment.dto.LabelDTO;
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
        return MomentDtoToMeta.metaListToDtoList(res);
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
     * @param momentDTO
     * @param files
     * @param labels
     * @return
     */
    @Override
    public boolean addMoment(MomentDTO momentDTO,List<String> files, List<LabelDTO> labels) {
        Moment moment = MomentDtoToMeta.dtoToMeta(momentDTO);
        momentDao.addMoment(moment);
        for(String file : files) {
            momentDao.addFile(file,moment.getMomentId());
        }
        for(LabelDTO label : labels) {
            momentDao.addLabel(moment.getMomentId(), label.getLabelId());
        }
        return true;
    }

    /**
     *
     * @param momentId
     * @return
     */
    @Override
    public boolean deleteMoment(Long momentId) {
        momentDao.deleteMoment(momentId);
        return true;
    }

    /**
     * 增加状态下某张图片的浏览量
     * @param resourceObj
     */
    @Override
    public void incrementViews(String resourceObj) {
        momentDao.incrementViews(resourceObj);
    }
}
