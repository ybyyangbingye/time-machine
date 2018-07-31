package com.netease.timemachine.timeset.service;

import com.netease.timemachine.timeset.dto.TimeSetDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 每月21号生成上个月不同标签的时光集壳子，生成时抓取上个月上传数量最多的3个标签（前提是上传照片满12张）。
     * 每个标签中的图片必须超过12张，选取12张浏览量最高的
     * @param childId
     * @return
     */
    Map<String, List<String>> searchLastMonthByLabels(Long childId);

    /**
     * 添加时光集文件
     * @param file
     * @param setId
     */
    void addTimeSetFile(String file,Long setId);

    /**
     * 添加一条时光集
     * @param timeSetDTO
     */
    void addTimeSet(TimeSetDTO timeSetDTO);

    /**
     * 查询时光集是否已经生成
     * @param setName
     * @return
     */
    boolean isExist(String setName);
}
