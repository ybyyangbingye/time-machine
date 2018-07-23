package com.netease.timemachine.milestone.vo;

import com.netease.timemachine.account.meta.User;
import com.netease.timemachine.common.dto.LabelDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午5:57
 */
@Data
public class MilestoneEventVO {
    private Long id;

    private Long milestoneId;
    // 1-里程碑
    private Integer type;

    private String location;

    private Date time;

    private List<String> images;

    private List<User> remindedUsers;

    private List<LabelDTO> labelDTOS;


}
