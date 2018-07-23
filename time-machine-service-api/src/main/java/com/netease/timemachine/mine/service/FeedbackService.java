package com.netease.timemachine.mine.service;


import com.netease.timemachine.mine.dto.FeedBackDTO;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午12:49
 */

public interface FeedbackService {

    boolean addFeedback(FeedBackDTO feedBackDTO);
}
