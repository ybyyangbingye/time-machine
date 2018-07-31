package com.netease.timemachine.milestone.dto;

import com.netease.timemachine.common.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午6:46
 */
@Data
public class MilestoneEventDTO extends BaseDTO {

    private Long milestoneId;

    private String content;

    private String location;

    private Date time;

}
