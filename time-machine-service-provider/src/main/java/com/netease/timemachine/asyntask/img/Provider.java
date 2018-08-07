/**
 * @(#)Provider.java, 2018/8/7.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.asyntask.img;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Configuration
public class Provider {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
