package com.netease.timemachine.meta;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 13:49 2018/7/16
 **/
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 7544931720749073701L;

    private Long userId;

    private String userName;

    private String phone;

    private Integer identification;

    private String address;

    private String imgUrl;

    private Date createTime;

    public User(String userName, String phone, Integer identification, String imgUrl, Date createTime) {
        this.userName = userName;
        this.phone = phone;
        this.identification = identification;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
    }
}
