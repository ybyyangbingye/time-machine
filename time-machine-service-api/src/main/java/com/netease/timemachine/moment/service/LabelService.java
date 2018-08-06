package com.netease.timemachine.moment.service;

import com.netease.timemachine.moment.dto.LabelDTO;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午3:03
 */
public interface LabelService {

    /**
     * 获得历史标签
     * @param userId
     * @param childId
     * @return
     */
    List<String> getHistoryLabels(Long userId, Long childId);

    /**
     * 获得家人标签
     * @param userId
     * @param childId
     * @return
     */
    List<String> getFamilyLabels(Long userId, Long childId);

    /**
     * 获得推荐标签
     * @param userId
     * @param childId
     * @return
     */
    List<String> getRecommendLabels(Long userId, Long childId);

    /**
     * 添加标签
     * @param userId
     * @param childId
     * @param labels
     */
    void addLabels(Long userId, Long childId, Long momentId, List<String> labels);
}
