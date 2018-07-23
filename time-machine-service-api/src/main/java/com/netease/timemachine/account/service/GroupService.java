package com.netease.timemachine.account.service;

import com.netease.timemachine.account.dto.GroupDTO;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:09 2018/7/20
 **/
public interface GroupService {

    /**
     * 用户添加一个孩子记录
     * 主动添加/接受申请
     * @param groupDTO
     */
    void insertGroup(GroupDTO groupDTO);
}
