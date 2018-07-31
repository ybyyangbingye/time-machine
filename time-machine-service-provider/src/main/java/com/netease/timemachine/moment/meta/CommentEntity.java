package com.netease.timemachine.moment.meta;

import lombok.Data;

import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 14:59 2018/7/25
 **/
@Data
public class CommentEntity {

    private Long momentId;

    private Moment moment;

    private List<Comment> comments;
}
