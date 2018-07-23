/**
 * @(#)starter.java, 2018/7/23.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.monitor;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Component
public class starter {

    @Autowired CuratorFramework client;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void ping(){
        System.out.println(client.getNamespace());

    }
}
