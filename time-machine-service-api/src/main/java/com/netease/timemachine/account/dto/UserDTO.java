package com.netease.timemachine.account.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:16 2018/7/16
 **/
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 5548162118009286016L;

    private Long userId;

    private String userName;

    private String phone;

    private String imgUrl;

    public UserDTO(String userName, String phone, String imgUrl) {
        this.userName = userName;
        this.phone = phone;
        this.imgUrl = imgUrl;
    }
}
