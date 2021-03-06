package com.netease.timemachine.moment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 14:39
 */

@Data
@NoArgsConstructor
public class LabelDTO implements Serializable {

    private static final long serialVersionUID = -8106807216081609073L;

    private Long labelId;

    private Long groupId;

    private String labelName;

    private Long userId;

    private Long childId;

    private Date gmtCreate;

    private Date gmtModified;
}
