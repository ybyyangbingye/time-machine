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

    private Long groupId;

    /**评论对象类型（1：状态，2：里程牌）*/
    private Integer groupType;

    private String content;

    private Long replyId;

    private String replyNickName;

    private Long parentId;

    private String parentNickName;

    private Date createTime;

    /**添加一条新的评论时，传递一个新的childId查询昵称*/
    private Long childId;

}
