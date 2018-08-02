package com.netease.timemachine.moment.service;

import com.alibaba.fastjson.JSONArray;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 20:17 2018/7/25
 **/
public interface LocationService {
    /**
     * 定位，显示周围位置信息
     * @param location 经纬度
     * @param page_size 每页显示数量
     * @param page_index 当前页，1开始
     * @return
     * @throws Exception
     */
    JSONArray searchPosByPoint(String location, Integer page_size,
                                      Integer page_index) throws Exception;

    /**
     * 根据搜索显示定位信息
     * @param location 经纬度
     * @param keyword 搜索关键词
     * @param page_size 每页显示数量
     * @param page_index 当前页，1开始
     * @return
     * @throws Exception
     */
    JSONArray searchPosByKeyWord(String location,String keyword,
                                        Integer page_size,Integer page_index) throws Exception;
}
