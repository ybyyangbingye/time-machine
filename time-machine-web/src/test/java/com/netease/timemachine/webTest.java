/**
 * @(#)Test.java, 2018/7/22.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine;

import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.netease.timemachine.auth.meta.RsaAlgorithm;
import com.netease.timemachine.message.util.KafkaUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class webTest {

    @Autowired RsaAlgorithm rsaAlgorithm;
    @Test
    public void contextLoads() {
        Map<String,Object> maps = new HashMap<>();
        maps.put("username","111");
        maps.put("phone","15");
        maps.put("auth","root");
        String token = rsaAlgorithm.create(null,maps);
        System.out.println(token);
    }

}
