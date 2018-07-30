/**
 * @(#)NosDto.java, 2018/7/23.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.nos.dto;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
public class NosDTO {

    String bucket;
    String object;
    Integer expires;
    Long objectSizeMin;
    Long objectSizeMax;
    String mimeLimit;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public Long getObjectSizeMin() {
        return objectSizeMin;
    }

    public void setObjectSizeMin(Long objectSizeMin) {
        this.objectSizeMin = objectSizeMin;
    }

    public Long getObjectSizeMax() {
        return objectSizeMax;
    }

    public void setObjectSizeMax(Long objectSizeMax) {
        this.objectSizeMax = objectSizeMax;
    }

    public String getMimeLimit() {
        return mimeLimit;
    }

    public void setMimeLimit(String mimeLimit) {
        this.mimeLimit = mimeLimit;
    }
}
