package com.netease.timemachine.moment.meta;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 14:40
 */

public class Label implements Serializable {

    private static final long serialVersionUID = 8639490206324568328L;

    private Long lableId;

    private String lableName;

    private Long lableType;

    private Long userId;

    private Date gmtCreate;

    private Date gmtModified;
}
