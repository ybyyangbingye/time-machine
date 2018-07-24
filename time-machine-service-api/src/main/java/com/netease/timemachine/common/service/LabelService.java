package com.netease.timemachine.common.service;

import com.netease.timemachine.common.dto.LabelDTO;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/24 下午3:03
 */
public interface LabelService {

    List<LabelDTO> getLabelsByIds(List<Long> ids);
}
