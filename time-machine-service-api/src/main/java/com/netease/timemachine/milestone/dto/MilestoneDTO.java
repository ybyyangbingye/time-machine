package com.netease.timemachine.milestone.dto;

import com.netease.timemachine.common.dto.BaseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:21
 */
@Data
@NoArgsConstructor
public class MilestoneDTO extends BaseDTO implements Serializable {

    protected String name;

    private Date time;

    protected Long childId;

    protected Long userId;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
