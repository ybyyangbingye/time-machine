package com.netease.timemachine.account.util;

import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.meta.User;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 17:47 2018/7/18
 **/
public class UserDtoToMetaUtil {
    public static User UserDtoToMeta(UserDTO userDTO){
        if(userDTO == null){
            return null;
        }
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setPhone(userDTO.getPhone());
        user.setImgUrl(userDTO.getImgUrl());
        return user;
    }

    public static UserDTO MetaToUserDto(User user){
        if(user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPhone(user.getPhone());
        userDTO.setImgUrl(user.getImgUrl());
        return userDTO;
    }
}
