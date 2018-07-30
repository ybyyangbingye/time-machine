package com.netease.timemachine.timeset.service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/24 14:05
 */
public interface TimeSetService {
    /**
     * 通过孩子id来获取上个月状态、里程牌的图片，排序规则为浏览量
     * 返回图片资源地址、浏览量
     * @param childId
     * @return
     */
    List<HashMap> searchLastMonthByViews(Long childId);
}
