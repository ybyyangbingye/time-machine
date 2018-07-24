package com.netease.timemachine.config;

import com.netease.timemachine.config.SmsAutoConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/23 14:36
 */
@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:smsconfig.properties")
public class SmsConfig {
    @Bean
    @ConfigurationProperties(prefix = "sms")
    public SmsAutoConfig getSmsAutozConfig(){
        return new SmsAutoConfig();
    }

}
