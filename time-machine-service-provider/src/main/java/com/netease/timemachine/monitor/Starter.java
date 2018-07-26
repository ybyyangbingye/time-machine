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
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Component
@RefreshScope
public class Starter {


    @Value("${monitor.name}")
    String appName;

    @Value("${env}")
    String env;
    @Autowired CuratorFramework client;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void ping() throws Exception {
        Stat stat = client.checkExists().forPath("/service/"+appName);
        DataStruct dataStruct = new DataStruct();
        dataStruct.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        dataStruct.setFreeMemory(Runtime.getRuntime().freeMemory()/1024/1024);
        dataStruct.setMaxMemory(Runtime.getRuntime().maxMemory()/1024/1024);
        dataStruct.setTotalMemory(Runtime.getRuntime().totalMemory()/1024/1024);
        Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
        Map<String, StackTraceElement[]> target = new HashMap<>();
        maps.keySet().stream().forEach((t)->{
            target.put(String.valueOf(t.getId())+";"+t.getName(),maps.get(t));
        });
        dataStruct.setThreadCount(maps.keySet().size());
        dataStruct.setThreads(target);
        if (stat == null) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/service/" + appName,JSON.toJSONString(dataStruct).getBytes());
        } else {
            client.setData().forPath("/service/"+ appName,JSON.toJSONString(dataStruct).getBytes());
        }
    }
}
