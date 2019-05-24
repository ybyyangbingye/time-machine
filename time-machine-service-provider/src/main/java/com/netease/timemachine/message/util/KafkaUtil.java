///**
// * @(#)KafkaUtil.java, 2018/7/22.
// * <p/>
// * Copyright 2018 Netease, Inc. All rights reserved.
// * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.netease.timemachine.message.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//import com.alibaba.fastjson.JSON;
//import com.netease.timemachine.message.executor.MessageDigest;
//import com.netease.timemachine.message.meta.Message;
//
///**
// * @author 李育鑫(liyuxin02 @ corp.netease.com)
// */
//@Component
//@Configuration
//public class KafkaUtil {
//
//    public static final String configcenterTopic = "configcenter";
//    @Resource
//    private KafkaTemplate kafkaTemplate;
//
//    @Resource MessageDigest messageDigest;
//    @KafkaListener(topics = configcenterTopic)
//    public void processMessage(String content) {
//        Message message = JSON.parseObject(content,Message.class);
//        messageDigest.digest(message);
//    }
//}
