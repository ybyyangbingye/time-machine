package com.netease.timemachine.common.service;

import com.netease.timemachine.common.dto.LabelBelongedDTO;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午2:17
 */
public interface LabelBelongedService {
    // 为动态添加标签
    boolean addLabelBelonged(LabelBelongedDTO belongedDTO);

    /**
     * 删除某个动态下的所有标签
     * @param groupType
     * @param groupId
     * @return
     */
    boolean deleteByGroupTypeAndGroupId(int groupType, long groupId);

    /**
     * 删除某个动态下的某个标签
     * @param groupType
     * @param groupId
     * @param labelId
     * @return
     */
    boolean deleteByGroupTypeAndGroupIdAndLabelId(int groupType, long groupId, long labelId);
}
