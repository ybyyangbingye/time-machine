package com.netease.timemachine.account.service;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/23 15:11
 */
public interface MsService {
    boolean sms(String phoneNumber) throws Exception;
    boolean vms(String phoneNumber, String code) throws Exception;
}
