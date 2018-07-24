package com.netease.timemachine.momment.service;

import com.netease.timemachine.momment.dto.MommentDTO;

import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:57
 */

public interface MommentService {

    /**
     * 获取用户某个宝宝的5条状态
     * @param userId
     * @param childId
     * @param currentPage
     * @return
     */
    List<MommentDTO> getMomments(Long userId, Long childId, Long currentPage);

    /**
     * 获取某个评论下的所有照片或视频（文件）
     * @param comment_id
     * @return
     */
    List<String> getMommentFiles(Long comment_id);


}
