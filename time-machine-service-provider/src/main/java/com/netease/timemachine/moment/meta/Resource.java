package com.netease.timemachine.moment.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:39
 */

@Data
@NoArgsConstructor
public class Resource implements Serializable, Comparable<Resource>{

    private static final long serialVersionUID = -5130970531109096859L;

    private Long file_id;

    private String resource_obj;

    private Integer resource_type;

    private Integer group_type;

    private Long group_id;

    private Date gmt_create;

    private Date gmt_modified;

    private Long views;

    /**
     * 定义比较器
     * @param o 比较对象
     * @return
     */
    @Override
    public int compareTo(Resource o) {
        return (int)(o.getViews()-this.views);
    }
}
