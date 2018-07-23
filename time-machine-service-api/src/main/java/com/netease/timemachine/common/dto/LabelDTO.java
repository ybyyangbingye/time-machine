package com.netease.timemachine.common.dto;

import lombok.Data;

/**
 * 标签
 *
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午7:59
 */
@Data
public class LabelDTO extends BaseDTO {

    private String name;

    private Integer type;

    private Long userId;
}
