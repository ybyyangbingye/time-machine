package com.netease.timemachine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/23 15:22
 */
@Data
public class SmsAutoConfig {
    private String serverUrl;
    private String verifyUrl;
    private String appKey;
    private String appSecret;
    private String nonce;
    private String templateId;
    private String codeLength;
}
