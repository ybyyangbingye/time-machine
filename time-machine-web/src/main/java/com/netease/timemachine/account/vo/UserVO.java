package com.netease.timemachine.account.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:16 2018/7/18
 **/
@Data
@NoArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 8557993167643151987L;

    private Long userId;

    private String userName;

    private String phone;

    private String imgUrl;

    public UserVO(String userName, String phone, String imgUrl) {
        this.userName = userName;
        this.phone = phone;
        this.imgUrl = imgUrl;
    }
}
