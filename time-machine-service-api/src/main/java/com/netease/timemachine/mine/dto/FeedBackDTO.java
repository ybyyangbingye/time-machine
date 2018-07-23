package com.netease.timemachine.mine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午1:36
 */
@Data
@NoArgsConstructor
public class FeedBackDTO implements Serializable {

    private static final long serialVersionUID = 1478027236204405621L;

    private Long id;

    private String content;

    private String imageObj;

    private Long userId;

    private Date gmtCreate;

    private Date gmtUpdate;


}
