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

    String Bucket;
    String Object;
    Integer Expires;
    Long ObjectSizeMin;
    Long ObjectSizeMax;
    String mimeLimit;

    public String getBucket() {
        return Bucket;
    }

    public void setBucket(String bucket) {
        Bucket = bucket;
    }

    public String getObject() {
        return Object;
    }

    public void setObject(String object) {
        Object = object;
    }

    public Integer getExpires() {
        return Expires;
    }

    public void setExpires(Integer expires) {
        Expires = expires;
    }

    public Long getObjectSizeMin() {
        return ObjectSizeMin;
    }

    public void setObjectSizeMin(Long objectSizeMin) {
        ObjectSizeMin = objectSizeMin;
    }

    public Long getObjectSizeMax() {
        return ObjectSizeMax;
    }

    public void setObjectSizeMax(Long objectSizeMax) {
        ObjectSizeMax = objectSizeMax;
    }

    public String getMimeLimit() {
        return mimeLimit;
    }

    public void setMimeLimit(String mimeLimit) {
        this.mimeLimit = mimeLimit;
    }
}
