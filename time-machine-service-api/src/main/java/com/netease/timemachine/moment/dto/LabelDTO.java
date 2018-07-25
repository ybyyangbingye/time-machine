package com.netease.timemachine.moment.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 14:39
 */

public class LabelDTO implements Serializable {

    private static final long serialVersionUID = -8106807216081609073L;

    private Long lableId;

    private String lableName;

    private Long lableType;

    private Long userId;

    private Date gmtCreate;

    private Date gmtModified;
}
