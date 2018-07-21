package com.netease.timemachine.account.util;

import com.netease.timemachine.dto.UserDTO;
import com.netease.timemachine.account.vo.UserVO;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 19:26 2018/7/18
 **/
public class UserVoToDtoUtil {

    public static UserVO UserDtoToVo(UserDTO userDTO){
        if(userDTO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setUserId(userDTO.getUserId());
        userVO.setUserName(userDTO.getUserName());
        userVO.setPhone(userDTO.getPhone());
        userVO.setAddress(userDTO.getAddress());
        userVO.setImgUrl(userDTO.getImgUrl());
        return userVO;
    }

    public static UserDTO UserVoToDto(UserVO userVO){
        if(userVO == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userVO.getUserId());
        userDTO.setUserName(userVO.getUserName());
        userDTO.setPhone(userVO.getPhone());
        userDTO.setImgUrl(userVO.getImgUrl());
        userDTO.setAddress(userVO.getAddress());
        return userDTO;
    }
}
