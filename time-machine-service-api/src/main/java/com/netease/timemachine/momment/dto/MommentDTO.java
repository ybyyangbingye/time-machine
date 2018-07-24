package com.netease.timemachine.momment.dto;

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
public class MommentDTO implements Serializable {

    private static final long serialVersionUID = -956037917886861599L;

    private Long momment_id;

    private Long creator_id;

    private String description;

    private Long tag_id;

    private Long child_id;

    private Date gmt_create;

    private Date gmt_modified;
}
