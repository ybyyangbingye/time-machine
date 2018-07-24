package com.netease.timemachine.common.service;

import com.netease.timemachine.common.dto.UserRemindedDTO;

/**
 * 被提醒人相关接口
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午1:33
 */
public interface UserRemindedService {
    /**
     * 添加被提醒人
     * @param userRemindedDTO
     * @return
     */
    boolean addUserReminded(UserRemindedDTO userRemindedDTO);

    /**
     * 删除指定的某个被提醒人
     * @param userId
     * @return
     */
    boolean deleteUserRemindedByUserIdAndGroupTypeAndGroupId(long userId, int groupType, long groupId);

    /**
     * 根据组类型和组id删除下面的所有被提醒人
     * @param groupType
     * @param groupId
     * @return
     */
    boolean deleteUserRemindedByGroupTypeAndGroupId(int groupType, long groupId);
}
