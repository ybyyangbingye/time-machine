package com.netease.timemachine.moment.service;

import com.netease.timemachine.moment.dto.ResourceDTO;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 10:24
 */

public interface ResourceService {


    /**
     * 获取朋友圈下一个状态的照片或视频
     * @param
     * */
    ResourceDTO getResource();
}
