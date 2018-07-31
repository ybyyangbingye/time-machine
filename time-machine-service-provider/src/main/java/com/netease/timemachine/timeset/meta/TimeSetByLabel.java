package com.netease.timemachine.timeset.meta;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: wqh
 * @description: 获取标签辅助类
 * @Date: Created in 10:06 2018/7/30
 **/
@Data
public class TimeSetByLabel implements Serializable {

    private static final long serialVersionUID = -288479149178985655L;

    private String name;

    private Integer groupType;

    private Long groupId;

}
