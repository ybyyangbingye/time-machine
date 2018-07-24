package com.netease.timemachine.account.service;

import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.UserDTO;

import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:16 2018/7/16
 **/
public interface UserService {
    /**
     * 用户注册
     * @param userDTO
     */
    void insertUser(UserDTO userDTO);

    /**
     * 通过phone去查询用户信息
     * 验证是否注册过/展示用户信息
     * @param phone
     * @return
     */
    UserDTO selectByPhone(String phone);

    /**
     * 通过userId去查询
     * @param userId
     * @return
     */
    UserDTO selectById(Long userId);

    /**
     * 查询用户userId所有的孩子信息
     * @param userId
     * @return
     */
    List<ChildDTO> selectOwnChildren(Long userId);

    /**
     * 编辑更新
     * @param userDTO
     */
    void updateUser(UserDTO userDTO);

    /**
     * 根据id集合获取用户信息集合
     * @param ids
     * @return
     */
    List<UserDTO> listUsersByIds(List<Long> ids);

}
