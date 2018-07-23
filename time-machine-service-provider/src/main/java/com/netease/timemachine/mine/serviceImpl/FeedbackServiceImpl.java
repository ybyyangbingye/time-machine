package com.netease.timemachine.mine.serviceImpl;

import com.netease.timemachine.mine.dao.FeedbackDao;
import com.netease.timemachine.mine.dto.FeedBackDTO;
import com.netease.timemachine.mine.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午1:58
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public boolean addFeedback(FeedBackDTO feedBackDTO) {
        feedBackDTO.setGmtCreate(new Date());
        return feedbackDao.addFeedback(feedBackDTO);
    }
}
