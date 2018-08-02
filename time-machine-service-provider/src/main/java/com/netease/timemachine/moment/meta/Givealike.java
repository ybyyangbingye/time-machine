package com.netease.timemachine.moment.meta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/26 09:57
 */
@Data
@AllArgsConstructor
public class Givealike implements Serializable{

    private static final long serialVersionUID = 6401987480840121155L;

    private Long userId;
    private Long groupId;
    private String nickname;

}
