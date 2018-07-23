/**
 * @(#)starter.java, 2018/7/23.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.monitor;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Component
public class starter {


    @Value("${monitor.name}")
    String appName;

    @Autowired CuratorFramework client;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void ping() throws Exception {
        Stat stat = client.checkExists().forPath("/service/"+appName);
        DataStruct dataStruct = new DataStruct();
        dataStruct.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        dataStruct.setFreeMemory(Runtime.getRuntime().freeMemory());
        dataStruct.setMaxMemory(Runtime.getRuntime().maxMemory());
        dataStruct.setTotalMemory(Runtime.getRuntime().totalMemory());
        Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
        dataStruct.setThreadCount(maps.keySet().size());
        if (stat == null) {
            client.create().withMode(CreateMode.EPHEMERAL).forPath("/service/" + appName,JSON.toJSONString(dataStruct).getBytes());
        } else {
            client.setData().forPath("/service/" + appName,JSON.toJSONString(dataStruct).getBytes());
        }
    }
}
