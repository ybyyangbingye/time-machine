package com.netease.timemachine.common.serviceImpl;

import com.netease.timemachine.common.dao.LabelDao;
import com.netease.timemachine.common.dto.LabelDTO;
import com.netease.timemachine.common.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午3:16
 */
@Service
public class LabelServiceImpl implements LabelService{

    @Autowired
    private LabelDao labelDao;

    @Override
    public List<LabelDTO> getLabelsByIds(List<Long> ids) {
        return labelDao.getLabelsByIds(ids);
    }

    /**
     * 获得历史标签
     * @param userId
     * @param childId
     * @return
     */
    @Override
    public List<String> getHistoryLabels(Long userId, Long childId) {
        return labelDao.getHistoryLabels(userId, childId);
    }

    /**
     * 获得家人标签
     * @param userId
     * @param childId
     * @return
     */
    @Override
    public List<String> getFamilyLabels(Long userId, Long childId) {
        List<String> res1 = labelDao.getFamilyLabelsFromLabel(userId, childId);
        List<String> res2 = labelDao.getFamilyLabelsFromUCG(childId);
        if(res1.size() == res2.size()) {
            return res1;
        }
        for(String s : res2) {
            if(!res1.contains(s)) {
                labelDao.insertFamilyLabel(userId,childId,s);
            }
        }
        return res2;
    }

    /**
     * 获得推荐标签
     * @param userId
     * @param childId
     * @return
     */
    @Override
    public List<String> getRecommendLabels(Long userId, Long childId) {
        return labelDao.getRecommendLabels(userId, childId);
    }

    /**
     * 添加标签
     * @param userId
     * @param childId
     * @param labels
     */
    @Override
    public void addLabels(Long userId, Long childId, List<String> labels) {
        for(String s : labels) {
            List<Long> type = labelDao.searchLabel(userId,childId,s);
            if(type == null) {
                labelDao.addLabel(userId,childId,s);
            }
            else if(type.size() == 1){
                labelDao.addHistoryLabel(userId,childId,s);
            }
            else {
                labelDao.updateLabel(userId,childId,s);
            }
        }
    }
}
