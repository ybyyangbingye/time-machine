/**
 * @(#)JWTVerify.java, 2018/7/19.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JWTVerify {
}

