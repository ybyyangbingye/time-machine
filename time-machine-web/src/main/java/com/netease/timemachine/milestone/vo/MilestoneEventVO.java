package com.netease.timemachine.milestone.vo;

import com.netease.timemachine.account.dto.UserDTO;
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
    // 里程碑事件id
    private Long id;
    // 里程碑id
    private Long milestoneId;

    private String location;

    private Date time;

    private List<String> resources;
    // 1-图片；2-视频
    private Integer resourceType;
    // 1-里程碑; 2-朋友圈
    private Integer groupType;

    private List<UserDTO> remindedUsers;

    private List<LabelDTO> labels;

    private Date gmtCreate;

    private Date gmtModified;


}
