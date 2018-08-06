package com.netease.timemachine.moment.meta;

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
public class Moment implements Serializable {

    private static final long serialVersionUID = 4443232323446573693L;

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

    public Moment(Long momentId, Long groupType, Long creatorId, String title, Integer resourceType, String description, String location, Long childId, Date gmtCreate, Date gmtModified) {
        this.momentId = momentId;
        this.groupType = groupType;
        this.creatorId = creatorId;
        this.title = title;
        this.resourceType = resourceType;
        this.description = description;
        this.location = location;
        this.childId = childId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
