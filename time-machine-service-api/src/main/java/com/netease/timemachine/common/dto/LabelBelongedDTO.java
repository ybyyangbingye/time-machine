package com.netease.timemachine.common.dto;

import lombok.Data;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午2:10
 */
@Data
public class LabelBelongedDTO extends BaseDTO{
    // 标签id
    private long labelId;
    //1-里程碑；2-朋友圈
    private int groupType;
    // 动态id
    private long groupId;
}
