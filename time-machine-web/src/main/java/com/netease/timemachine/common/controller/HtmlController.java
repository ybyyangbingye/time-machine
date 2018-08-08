package com.netease.timemachine.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: wqh
 * @description: 返回html页面
 * @Date: Created in 15:08 2018/8/8
 **/

@Controller
@RequestMapping("/wechat")
public class HtmlController {

    @GetMapping("/index")
    public String returnIndex(){
        return "index";
    }

    @GetMapping("/success")
    public String returnSuccess() {
        return "success";
    }

    @GetMapping("/toQRcode")
    public String toQRcode(){
        return "code";
    }
}
