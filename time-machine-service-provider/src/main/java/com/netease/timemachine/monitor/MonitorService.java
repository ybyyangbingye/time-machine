/**
 * @(#)MonitorService.java, 2018/7/23.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.monitor;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Component
public class MonitorService {

    public void cancelThread(String threadId){
        Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
        maps.keySet().stream().forEach(thread ->{
            if (thread.getId()==Long.valueOf(threadId)){
                thread.interrupt();
            }
        });
    }
}
