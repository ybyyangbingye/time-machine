package com.netease.timemachine.moment.service;

import com.netease.timemachine.moment.dto.LabelDTO;
import com.netease.timemachine.moment.dto.MomentDTO;

import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:57
 */

public interface MomentService {

    /**
     * 获取用户某个宝宝的5条状态
     * @param userId
     * @param childId
     * @param currentPage
     * @return
     */
    List<MomentDTO> getMoments(Long userId, Long childId, Long currentPage);

    /**
     * 获取某个评论下的所有照片或视频（文件）
     * @param momentId
     * @return
     */
    List<String> getMomentFiles(Long momentId);

    /**
     * 用户添加一条关于宝宝的状态
     * @param moment
     * @param files
     * @param labels
     * @return
     */
    boolean addMoment(MomentDTO moment,List<String> files, List<LabelDTO> labels);

    /**
     * 用户删除宝宝的状态
     * @param momentId
     * @return
     */
    boolean deleteMoment(Long momentId);

    /**
     * 更新状态下的某张图片的浏览量
     * @param resourceObj
     */

    void incrementViews(String resourceObj);
}
