/**
 * @(#)BaseAlgorithm.java, 2018/7/19.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.auth.meta;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
public interface BaseAlgorithm {

    void init() throws Exception;

}
