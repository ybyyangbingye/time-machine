/**
 * @(#)VerifyHandler.java, 2018/7/19.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.auth.handler;

import com.netease.timemachine.auth.annotation.JWTVerify;
import com.netease.timemachine.auth.meta.RsaAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Component
public class VerifyHandler implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(VerifyHandler.class);

    @Autowired
    private RsaAlgorithm rsaAlgorithm;

    @Override
    public boolean preHandle(HttpServletRequest res, HttpServletResponse resp, Object o) throws Exception {
        if (o instanceof HandlerMethod) {
            JWTVerify jwtVerify = ((HandlerMethod) o).getMethodAnnotation(JWTVerify.class);
            if (jwtVerify == null) {
                return true;
            }
            try {
                String token = res.getHeader("Authorization");
                rsaAlgorithm.verify(token);
            } catch (Exception e) {
                LOG.error("JWT验证失败 ip = {}",res.getRemoteAddr() ,e);
                System.out.println();
                System.out.println("test合并到master");
                return false;
            }
        }
        return true;
    }
}
