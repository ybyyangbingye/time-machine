/**
 * @(#)Message.java, 2018/7/22.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.message.meta;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
public class Message<T> {

    String cmd;

    T data;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
