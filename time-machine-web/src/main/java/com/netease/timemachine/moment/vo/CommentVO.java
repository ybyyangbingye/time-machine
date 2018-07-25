package com.netease.timemachine.moment.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:32 2018/7/25
 **/
@Data
@NoArgsConstructor
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 2951628110786214749L;

    private Long commentId;

    private Long momentId;

    private String content;

    private Long replyId;

    private String replyNickName;

    private Long parentId;

    private String parentNickName;

    private Date createTime;

}
