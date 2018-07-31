package com.netease.timemachine.milestone.dto;

import com.netease.timemachine.common.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/31 下午8:06
 */
@Data
public class MilestoneEventLoverDTO extends BaseDTO{
    // 喜欢动态的用户的id
    private Long from;
    // 动态id
    private Long groupId;
}
