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
     * 获取用户的5条状态
     * @param childId
     * @param currentPage
     * @return
     */
    @Override
    public List<MomentDTO> getMoments(Long childId, Long currentPage, Long type) {
        if(type == 1L) {
            List<Moment> res = momentDao.getAllMoments(childId, type);
            return MomentDtoToMeta.metaListToDtoList(res);
        }
        else {
            Long start = currentPage * 5;
            List<Moment> res = momentDao.getMoments(childId, start, type);
            return MomentDtoToMeta.metaListToDtoList(res);
        }
    }

    /**
     * 获取状态下的文件
     * @param moment_id
     * @return
     */
    @Override
    public List<String> getMomentFiles(Long moment_id) {
        List<String> res = momentDao.getMomentFiles(moment_id);
        return res;
    }

    /**
     * 获取状态下的标签
     * @param momentId
     * @return
     */
    @Override
    public List<String> getMomentLabels(Long momentId) {
        List<String> res = momentDao.getMomentLabels(momentId);
        return res;
    }

    @Override
    public String getNickName(Long childId, Long userId) {
        String nickName = momentDao.getNickname(childId, userId);
        return nickName;
    }

    /**
     *
     * @param momentDTO
     * @param files
     * @return
     */
    @Override
    public Long addMoment(MomentDTO momentDTO,List<String> files) {
        Moment moment = MomentDtoToMeta.dtoToMeta(momentDTO);
        momentDao.addMoment(moment);
        for(String file : files) {
            momentDao.addFile(file,moment.getMomentId(),moment.getGroupType());
        }
        return moment.getMomentId();
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

    @Override
    public List<MomentDTO> listMomentByUserId(long userId) {
        return momentDao.listMomentByUserId(userId);
    }

}
