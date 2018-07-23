package com.netease.timemachine.common.dto;

import lombok.Data;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午6:53
 */
@Data
public class ResourceDTO extends BaseDTO{

    private String resourceObj;

    private Integer type;

    private Long parentId;

}
