package com.netease.timemachine.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午4:24
 */
@Data
public class BaseDTO {

    protected Long id;

    protected Date gmtCreate;

    protected Date gmtModified;
}
