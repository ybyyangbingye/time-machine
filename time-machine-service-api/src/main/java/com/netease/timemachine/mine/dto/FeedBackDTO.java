package com.netease.timemachine.mine.dto;

import com.netease.timemachine.common.dto.BaseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午1:36
 */
@Data
@NoArgsConstructor
public class FeedBackDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1478027236204405621L;

    private String content;

    private String imageObj;

    private Long userId;


}
