package com.netease.timemachine.mine.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午2:45
 */
@Data
public class MyResourceVO {

    private String resourceObj;

    private Long groupId;
    // 动态内内容
    private String content;
    // 评论数量
    private Integer commentCount;
    // 喜欢的人数量
    private Integer loverCount;
    // 资源创建时间
    private Date gmtCreate;

}
