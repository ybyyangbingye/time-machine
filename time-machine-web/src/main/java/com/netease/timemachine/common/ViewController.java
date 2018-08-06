package com.netease.timemachine.common;

import com.netease.timemachine.common.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/30 下午3:42
 */
@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 更新浏览量
     * @param request
     * @param resourceObj 资源key值
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public boolean updateViews(HttpServletRequest request, @RequestParam("resourceObj") String resourceObj) {
        return resourceService.updateViewsByResourceObj(resourceObj);
    }
    @RequestMapping("test")
    public String test(){
        return "JE";
    }
}
