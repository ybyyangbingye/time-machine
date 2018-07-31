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
    void addGivealike(GivealikeDTO givealikeDTO);
    void deletealike(GivealikeDTO givealikeDTO);
    List<String> getAll(Long momentId);
    boolean isGivealike(Long userId, Long momentId);
}