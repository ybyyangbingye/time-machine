package com.netease.timemachine.account.meta;

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

    private String imgUrl;

    private Date createTime;

    public User(String userName, String phone, String imgUrl, Date createTime) {
        this.userName = userName;
        this.phone = phone;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
    }
}
