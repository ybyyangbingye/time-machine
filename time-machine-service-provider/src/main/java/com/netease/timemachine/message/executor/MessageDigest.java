/**
 * @(#)MessageDigest.java, 2018/7/24.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.message.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netease.timemachine.message.meta.Message;
import com.netease.timemachine.message.meta.MessageCmd;
import com.netease.timemachine.monitor.MonitorService;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Component
public class MessageDigest {
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    @Autowired MonitorService monitorService;
    public void digest(Message message){
        if (MessageCmd.cancelThread.equals(message.getCmd())){
            String threadId = (String) message.getData();
            singleThreadExecutor.execute(()->{
                monitorService.cancelThread(threadId);
            });
        }
    }

}
