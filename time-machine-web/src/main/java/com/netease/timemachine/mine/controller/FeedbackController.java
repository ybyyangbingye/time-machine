package com.netease.timemachine.mine.controller;

import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.mine.dto.FeedBackDTO;
import com.netease.timemachine.mine.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/23 下午12:47
 */
@RestController
@RequestMapping("/mine/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity submitFeedback(HttpServletRequest request, @RequestBody FeedBackDTO feedBackDTO) {
        feedbackService.addFeedback(feedBackDTO);
        return ResponseView.success("", "反馈成功");
    }

}
