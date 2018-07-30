package com.netease.timemachine.milestone.vo;

import com.netease.timemachine.milestone.dto.MilestoneDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/27 上午9:33
 */
@Data
public class MilestoneVO extends MilestoneDTO implements Serializable{

    private Integer childAge;

    private String coverObj;

    public MilestoneVO(MilestoneDTO milestoneDTO) {
        this.id = milestoneDTO.getId();
        this.name = milestoneDTO.getName();
        this.childId = milestoneDTO.getChildId();
        this.userId = milestoneDTO.getUserId();
        this.gmtCreate = milestoneDTO.getGmtCreate();
        this.gmtModified = milestoneDTO.getGmtModified();
    }

}
