package com.netease.timemachine.moment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:51
 */
@Data
@NoArgsConstructor
public class MomentDTO implements Serializable {

    private static final long serialVersionUID = -956037917886861599L;

    private Long momentId;

    private Long groupType;

    private Long creatorId;

    private String title;

    private Integer resourceType;

    private String description;

    private String location;

    private Long childId;

    private Date gmtCreate;

    private Date gmtModified;
}
