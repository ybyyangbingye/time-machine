package com.netease.timemachine.account.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.account.service.MsService;
import com.netease.timemachine.account.util.CheckSumBuilder;
import com.netease.timemachine.config.SmsAutoConfig;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZLS
 * @Description:
 * @Date: 2018/7/23 15:13
 */
@Service
public class MsServiceImpl implements MsService {
    @Autowired
    SmsAutoConfig smsAutoConfig;

    @Override
    public boolean sms(String phoneNumber) throws Exception {
        HttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(smsAutoConfig.getServerUrl());
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(smsAutoConfig.getAppSecret(), smsAutoConfig.getNonce(), curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", smsAutoConfig.getAppKey());
        httpPost.addHeader("Nonce", smsAutoConfig.getNonce());
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", smsAutoConfig.getTemplateId()));
        nvps.add(new BasicNameValuePair("mobile", phoneNumber));
        nvps.add(new BasicNameValuePair("codeLen", smsAutoConfig.getCodeLength()));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        String res = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(res);
        JSONObject jsonObject= JSONObject.parseObject(res);
        if(jsonObject.getInteger("code") != 200){
            return false;
        }
        return true;
    }

    @Override
    public boolean vms(String phoneNumber, String code) throws Exception {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(smsAutoConfig.getVerifyUrl());
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(smsAutoConfig.getAppSecret(), smsAutoConfig.getNonce(), curTime);

            // 设置请求的header
            httpPost.addHeader("AppKey", smsAutoConfig.getAppKey());
            httpPost.addHeader("Nonce", smsAutoConfig.getNonce());
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 设置请求的的参数，requestBody参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            /*
             * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
             * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
             * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
             */
            nvps.add(new BasicNameValuePair("mobile", phoneNumber));
            nvps.add(new BasicNameValuePair("code", code));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            /*
             * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
             * 2.具体的code有问题的可以参考官网的Code状态表
             */
        String res = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject= JSONObject.parseObject(res);
        if(jsonObject.getInteger("code") != 200){
            return false;
        }
        return true;
    }
}


