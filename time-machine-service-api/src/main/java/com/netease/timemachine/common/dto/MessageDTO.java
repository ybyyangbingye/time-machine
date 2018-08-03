package com.netease.timemachine.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MessageDTO {

    private Long messageId;

    private Long senderId;

    private Long receiverId;

    private Integer groupType;

    private Long groupId;

    private String content;

    private Date gmtCreate;


}
