package com.netease.timemachine.momment.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 11:18
 */
@Data
@NoArgsConstructor
public class MommentVO implements Serializable {

    private static final long serialVersionUID = 1152494284183844731L;

    private Long momment_id;

    private Long creator_id;

    private String description;

    private Long tag_id;

    private Long child_id;

    private Date gmt_create;

    private Date gmt_modified;

}
