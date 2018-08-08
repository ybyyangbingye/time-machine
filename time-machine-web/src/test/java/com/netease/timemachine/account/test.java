/**
 * @(#)test.java, 2018/8/7.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.netease.timemachine.asyntask.img.ImageService;
import com.netease.timemachine.common.dto.ResourceDTO;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired ImageService imageService;
    @Test
    public void t(){
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(100L);
        resourceDTO.setResourceType(2);
        System.out.println(123);
        resourceDTO.setResourceObj("https://v.youku.com/v_show/id_XMzA5NzkxNzA1Ng==.html?spm=a2h1n.8251843.playList.5~5~A&f=51266236&o=1");
        imageService.checkResult();
    }
}
