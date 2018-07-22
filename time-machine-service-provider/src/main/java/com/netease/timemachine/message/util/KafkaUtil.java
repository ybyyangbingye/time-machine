/**
 * @(#)KafkaUtil.java, 2018/7/22.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.message.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Component
@Configuration
public class KafkaUtil {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${spring.kafka.consumer.group-id}")
    private String t;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
