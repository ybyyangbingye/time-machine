package com.netease.timemachine.common.controller;

import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.dto.UserDTO;
import com.netease.timemachine.account.service.ChildService;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.service.UserService;
import com.netease.timemachine.account.util.ChildInvitationCode;
import com.netease.timemachine.account.util.ResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.netease.timemachine.account.enums.AccountEnum.BIND_FAILED;
import static com.netease.timemachine.account.enums.AccountEnum.BINED_REPEAT;
import static com.netease.timemachine.account.enums.AccountEnum.USER_NULL;

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
