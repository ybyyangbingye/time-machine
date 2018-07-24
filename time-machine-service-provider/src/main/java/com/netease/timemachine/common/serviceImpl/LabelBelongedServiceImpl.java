package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.LabelBelongedDao;
import com.netease.timemachine.common.dto.LabelBelongedDTO;
import com.netease.timemachine.common.service.LabelBelongedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午2:29
 */
@Service
public class LabelBelongedServiceImpl implements LabelBelongedService{

    @Autowired
    private LabelBelongedDao labelBelongedDao;

    @Override
    public boolean addLabelBelonged(LabelBelongedDTO labelBelongedDTO) {
        return labelBelongedDao.addLabelBelonged(labelBelongedDTO);
    }

    @Override
    public List<LabelBelongedDTO> getByGroupTypeAndGroupId(int groupType, long groupId) {
        return labelBelongedDao.getByGroupTypeAndGroupId(groupType, groupId);
    }

    @Override
    public boolean deleteByGroupTypeAndGroupId(int groupType, long groupId) {
        return labelBelongedDao.deleteByGroupTypeAndGroupId(groupType, groupId);
    }

    @Override
    public boolean deleteByGroupTypeAndGroupIdAndLabelId(int groupType, long groupId, long labelId) {
        return labelBelongedDao.deleteByGroupTypeAndGroupIdAndLabelId(groupType, groupId, labelId);
    }

    @Override
    public boolean deleteLabelBelongedById(long id) {
        return labelBelongedDao.deleteLabelBelongedById(id);
    }
}
