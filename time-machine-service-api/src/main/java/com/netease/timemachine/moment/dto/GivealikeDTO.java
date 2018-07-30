package com.netease.timemachine.moment.dto;

import com.netease.timemachine.common.dto.BaseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/25 17:19
 */
@Data
@NoArgsConstructor
public class GivealikeDTO implements Serializable {
    private static final long serialVersionUID = -2901094656712762124L;
    private long userId;
    private long momentId;
}
