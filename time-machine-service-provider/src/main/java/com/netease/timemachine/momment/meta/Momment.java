package com.netease.timemachine.momment.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:58
 */

@Data
@NoArgsConstructor
public class Momment implements Serializable {

    private static final long serialVersionUID = 4443232323446573693L;

    private Long momment_id;

    private Long creator_id;

    private String description;

    private Long tag_id;

    private Long child_id;

    private Date gmt_create;

    private Date gmt_modified;

    public Momment(Long momment_id, Long creator_id, String description, Long tag_id, Long child_id, Date gmt_create, Date gmt_modified) {
        this.momment_id = momment_id;
        this.creator_id = creator_id;
        this.description = description;
        this.tag_id = tag_id;
        this.child_id = child_id;
        this.gmt_create = gmt_create;
        this.gmt_modified = gmt_modified;
    }
}
