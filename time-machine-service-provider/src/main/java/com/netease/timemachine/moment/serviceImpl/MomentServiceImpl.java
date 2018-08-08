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

    @Override
    public List<Integer> getType(Long moment_id) {
        List<Integer> types = momentDao.getType(moment_id);
        return types;
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
     * 添加一条状态、里程碑
     * @param momentDTO
     * @param files
     * @return
     */
    @Override
    public Long addMoment(MomentDTO momentDTO,List<String> files) {
        Moment moment = MomentDtoToMeta.dtoToMeta(momentDTO);
        momentDao.addMoment(moment);
        for(String file : files) {
            momentDao.addFile(file,moment.getResourceType(),moment.getMomentId(),moment.getGroupType());
        }
        return moment.getMomentId();
    }

    /**
     * 删除一条状态、里程碑
     * @param momentId
     * @return
     */
    @Override
    public boolean deleteMoment(Long momentId) {
        momentDao.deleteMoment(momentId);
        return true;
    }

    /**
     * 获取所有被提醒的人
     * @param childId
     * @return
     */
    @Override
    public List<Long> getReceivers(Long childId) {
        return momentDao.getReceivers(childId);
    }

    @Override
    public List<MomentDTO> listMomentByUserId(long userId) {
        return momentDao.listMomentByUserId(userId);
    }

}
