package com.netease.timemachine.vo.account;

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

    private Integer identification;

    private String address;

    private String imgUrl;

    public UserVO(String userName, String phone, Integer identification, String address, String imgUrl) {
        this.userName = userName;
        this.phone = phone;
        this.identification = identification;
        this.address = address;
        this.imgUrl = imgUrl;
    }
}
