package com.netease.timemachine.timeset.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/24 13:54
 */

@Data
@NoArgsConstructor
public class TimeSetDTO implements Serializable {

    private static final long serialVersionUID = 4680242355739578048L;

    private Long setId;

    /**
     * 时光集命名规则
     * ①时间：以月命名，如：2018年7月。
     * ③标签：以标签+标签时间命名，如：吃饭+2018年5月。
     **/
    private String setName;

    private Long childId;

    private List<String> pictures;

    private String musicUrl;

    private Date createTime;

    public TimeSetDTO(String setName, Long childId, List<String> pictures, String musicUrl) {
        this.setName = setName;
        this.childId = childId;
        this.pictures = pictures;
        this.musicUrl = musicUrl;
    }
}
