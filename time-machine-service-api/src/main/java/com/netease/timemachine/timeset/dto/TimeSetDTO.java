package com.netease.timemachine.timeset.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
     * ②地点：以地点+地点时间命名，如：北京+2017年8月。
     * ③标签：以标签+标签时间命名，如：吃饭+2018年5月。
     **/
    private String setName;

    /**视频类型：1-时间，2-地点，3-标签*/
    private Integer setType;

    private Date createTime;
}
