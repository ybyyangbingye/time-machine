/**
 * @(#)NosController.java, 2018/7/23.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.nos;

import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.nos.dto.NosDTO;
import com.netease.timemachine.nos.service.NosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@RestController
@RequestMapping("/nos")
public class NosController {

    @Autowired NosService nosService;

    @RequestMapping("/token")
    public ResponseEntity token(@RequestBody NosDTO nosDTO){
        return ResponseView.success(nosService.create(nosDTO));
    }

    @RequestMapping("/beat")
    public ResponseEntity beat(){
        return ResponseView.success("beat");
    }

}
