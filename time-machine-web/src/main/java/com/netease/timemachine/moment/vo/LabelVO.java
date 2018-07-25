package com.netease.timemachine.moment.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 14:41
 */

public class LabelVO implements Serializable {

    private static final long serialVersionUID = -633526127223529049L;

    private Long lableId;

    private String lableName;

    private Long lableType;

    private Long userId;

    private Date gmtCreate;

    private Date gmtModified;
}
