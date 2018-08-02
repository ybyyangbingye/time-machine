package com.netease.timemachine.moment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:32 2018/7/24
 **/
@Data
@NoArgsConstructor
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 3639450191991972336L;

    private Long commentId;

    private Long groupId;

    private String content;

    private Long replyId;

    private String replyNickName;

    private Long parentId;

    private String parentNickName;

    private Date createTime;
}
