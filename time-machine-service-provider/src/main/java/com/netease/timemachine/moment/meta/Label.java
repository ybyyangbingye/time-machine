package com.netease.timemachine.moment.meta;

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

    private static final long serialVersionUID = 8639490206324568328L;

    private Long labelId;

    private Long groupId;

    private String labelName;

    private Long userId;

    private Long childId;

    private Date gmtCreate;

    private Date gmtModified;

    public Label(Long labelId, Long groupId, String labelName, Long userId, Long childId, Date gmtCreate, Date gmtModified) {
        this.labelId = labelId;
        this.groupId = groupId;
        this.labelName = labelName;
        this.userId = userId;
        this.childId = childId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
