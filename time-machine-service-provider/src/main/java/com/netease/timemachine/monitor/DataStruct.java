/**
 * @(#)DataStruct.java, 2018/7/23.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.monitor;

import java.util.List;
import java.util.Map;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
public class DataStruct {

    private int threadCount;
    private int availableProcessors;
    private long freeMemory;
    private long totalMemory;
    private long maxMemory;
    private long time;
    private Map<String, StackTraceElement[]> threads;

    public Map<String, StackTraceElement[]> getThreads() {
        return threads;
    }

    public void setThreads(Map<String, StackTraceElement[]> threads) {
        this.threads = threads;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }
}
