package com.netease.timemachine.moment.meta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/26 09:57
 */
@Data
@AllArgsConstructor
public class Givealike implements Serializable{

    private static final long serialVersionUID = 6401987480840121155L;

    private long id;
    private long userId;
    private long momentId;
    private String nickname;
    private Date gmtCreate;

    public Givealike(long userId, long momentId, String nickname) {
        this.userId = userId;
        this.momentId = momentId;
        this.nickname = nickname;
    }
}
