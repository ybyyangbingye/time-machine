package com.netease.timemachine.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MessageVO {

    private Long messageId;

    private Long senderId;

    private Long receiverId;

    private Integer groupType;

    private Long groupId;

    private String content;

    private Date gmtCreate;
}
