package com.netease.timemachine.service;

import com.netease.timemachine.dto.ChildDTO;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 20:14 2018/7/17
 **/
public interface ChildService {
    /**
     * 新增孩子信息
     * @param childDTO
     */
     Long insertChild(ChildDTO childDTO);

    /**
     * 根据孩子id获取孩子信息
     * @param childId
     */
    ChildDTO selectChildById(long childId);

    /**
     * 编辑更新孩子信息
     * @param childDTO
     */
    void updateChild(ChildDTO childDTO);
}
