package com.netease.timemachine.moment.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:09 2018/7/24
 **/
@Data
@NoArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID = 3073020984090517784L;

    private Long commentId;

    private Long momentId;

    private String content;

    private Long replyId;

    private Long parentId;

    private Date createTime;

}
