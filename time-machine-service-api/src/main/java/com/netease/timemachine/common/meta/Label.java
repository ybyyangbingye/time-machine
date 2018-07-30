package com.netease.timemachine.common.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 14:40
 */

@Data
@NoArgsConstructor
public class Label implements Serializable {

    private static final long serialVersionUID = -2830409180499082742L;

    private Long labelId;

    private String labelName;

    private Long labelType;

    private Long userId;

    private Long childId;

    private Date gmtCreate;

    private Date gmtModified;

    public Label(Long labelId, String labelName, Long labelType, Long userId, Long childId, Date gmtCreate, Date gmtModified) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.labelType = labelType;
        this.userId = userId;
        this.childId = childId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
