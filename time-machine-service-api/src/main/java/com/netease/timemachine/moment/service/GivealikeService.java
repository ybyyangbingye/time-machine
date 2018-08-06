package com.netease.timemachine.moment.service;

import com.netease.timemachine.moment.dto.GivealikeDTO;

import java.util.List;


/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/25 17:41
 */
public interface GivealikeService {
    String getNickname(GivealikeDTO givealikeDTO);
    Long addGivealike(GivealikeDTO givealikeDTO);
    void deletealike(GivealikeDTO givealikeDTO);
    List<GivealikeDTO> getAll(Long groupId);

    int getLoverCountByMomentId(Long groupId);

    boolean isGivealike(Long userId, Long groupId);

    /**
     * 获取被点赞状态/里程碑发表人的id，以接受消息提醒
     * @param groupId
     * @return
     */
    Long getLikedUser(Long groupId);
}