package com.netease.timemachine.common.controller;

import com.netease.timemachine.common.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/30 下午3:42
 */
@RestController
public class ViewController {

    @Resource
    private ResourceService resourceService;

    /**
     * 更新浏览量
     * @param resourceObj 资源key值
     * @param groupId 所属动态id
     * @return
     */
    @PostMapping("/view")
    public boolean recordView(@RequestParam("groupId") long groupId, @RequestParam("resourceObj") String resourceObj) {
        return resourceService.updateViewsByGroupIdAndResourceId(groupId, resourceObj);
    }
}
