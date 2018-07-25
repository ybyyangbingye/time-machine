package com.netease.timemachine.moment.service;

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
     * 用户添加一条关于孩子的状态
     * @param userId
     * @param childId
     * @param description
     * @param files
     * @return
     */
    boolean addMoment(Long userId, Long childId, String description, String location,
                       List<String> files);


    //boolean deleteMoment();
}
