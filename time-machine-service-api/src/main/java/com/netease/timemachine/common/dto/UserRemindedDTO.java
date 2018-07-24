package com.netease.timemachine.common.dto;

import lombok.Data;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午1:27
 */
@Data
public class UserRemindedDTO extends BaseDTO{
    // 被提醒人id
    private Long userId;
    // 被提醒人所属动态的id
    private Long groupId;
    // 1-里程碑；2-朋友圈
    private Integer groupType;

}
