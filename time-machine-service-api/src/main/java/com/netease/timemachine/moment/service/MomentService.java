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
     * @param childId
     * @param currentPage
     * @return
     */
    List<MomentDTO> getMoments(Long childId, Long currentPage, Long type);

    /**
     * 获取某个状态下的所有照片或视频（文件）
     * @param momentId
     * @return
     */
    List<String> getMomentFiles(Long momentId);

    /**
     *
     * @param moment_id
     * @return
     */
    List<Integer> getType(Long moment_id);

    /**
     * 获取某个状态下的所有标签
     * @param momentId
     * @return
     */
    List<String> getMomentLabels(Long momentId);

    /**
     * 获取昵称
     * @param childId
     * @param userId
     * @return
     */
    String getNickName(Long childId, Long userId);

    /**
     * 用户添加一条关于宝宝的状态
     * @param moment
     * @param files
     * @return
     */
    Long addMoment(MomentDTO moment,List<String> files);

    /**
     * 用户删除宝宝的状态
     * @param momentId
     * @return
     */
    boolean deleteMoment(Long momentId);

    /**
     * 获取发表状态后需要被提醒的人
     * @param childId
     * @return
     */
    List<Long> getReceivers(Long childId);

    /**
     * 根据用户id获取用户所有的动态
     * @param userId
     * @return
     */
    List<MomentDTO> listMomentByUserId(long userId);

}
