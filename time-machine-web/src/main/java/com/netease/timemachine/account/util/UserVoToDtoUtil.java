package com.netease.timemachine.account.util;

import com.netease.timemachine.account.dto.UserDTO;
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
        userVO.setImgUrl(userDTO.getImgUrl());
        userVO.setIsRegistered(userDTO.getIsRegistered());
        return userVO;
    }

    public static UserDTO UserVoToDto(UserVO userVO){
        if(userVO == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userVO.getUserId());
        userDTO.setUserName(userVO.getUserName());
        userDTO.setImgUrl(userVO.getImgUrl());
        userDTO.setPhone(userVO.getPhone());
        userDTO.setIsRegistered(userVO.getIsRegistered());

        return userDTO;
    }
}
