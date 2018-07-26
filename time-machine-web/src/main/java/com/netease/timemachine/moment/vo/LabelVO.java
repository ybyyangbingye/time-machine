package com.netease.timemachine.moment.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 14:41
 */

@Data
@NoArgsConstructor
public class LabelVO implements Serializable {

    private static final long serialVersionUID = -633526127223529049L;

    private Long labelId;

    private String labelName;

    private Long labelType;

    private Long userId;

    private Date gmtCreate;

    private Date gmtModified;
}
