package com.netease.timemachine.account.service;

import com.netease.timemachine.account.dto.GroupDTO;

import java.util.List;

/**
 * @author: wqh
 * @description:孩子管理类
 * @Date: Created in 15:09 2018/7/20
 **/
public interface GroupService {

    /**
     * 新增一条孩子用户关联
     * @param groupDTO
     */
    void insertGroup(GroupDTO groupDTO);

    /**
     * 通过userId和childId查询用户信息
     * @param userId
     * @param childId
     * @return
     */
    GroupDTO selectByUserAndChildId(Long userId, Long childId);

    /**
     * 删除一条孩子关联
     * 只有管理员才有权限删除
     * 管理员进行删除操作时，无法直接删除管理员，
     * 需要先修改为其它权限，然后才能进行删除。
     */
    void deleteGroup(Long userId, Long childId);

    /**
     * 管理员可以修改其他成员的身份、昵称、权限，但是不能修改创建者权限，可以修改创建者身份和昵称
     * 其他身份均可以修改自己的身份、昵称
     *
     */
    void UpdateGroup(GroupDTO groupDTO);

    /**
     * 获取关注childId的所有用户
     * @param childId
     */
    List<GroupDTO> selectGroupByChildId(Long childId);

    /**
     * 修改用户信息时，Group表里面的imgUrl信息也会更新
     * @param imgUrl
     * @param userId
     */
    void updateGroupImg(String imgUrl, Long userId);

    /**
     * 获取当前用户在当前孩子的管理权限
     * 权限（创建者：0，管理者：1，记录：2， 查看：3）
     * @param userId
     * @param childId
     * @return
     */
    Integer permissionById(Long userId, Long childId);

    /**
     * 查询childId的创建者（根据权限）
     * @param childId
     * @return
     */
    Long selectChildCreator(Long childId);
}
