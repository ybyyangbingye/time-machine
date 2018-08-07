/**
 * @(#)ImageService.java, 2018/8/7.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.asyntask.img;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.common.dao.ResourceDao;
import com.netease.timemachine.common.dto.ResourceDTO;
import com.netease.timemachine.moment.meta.Resource;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Service
public class ImageService {

    @Autowired ResourceDao resourceDao;
    @Autowired RestTemplate restTemplate;
    private static final String SECRET_ID = "090cbc98fc999f69b58424e89a817269";
    private static final String SECRET_KEY = "f1c4ba558ddfa449cf7b30d670d86d08";
    private static final String BUSINESS_ID = "be017655713de9e7c7da3a7d07479a0d";
    private final static String API_URL = "https://as.dun.163yun.com/v3/image/check";
    public void run(){
        List<ResourceDTO> lists = resourceDao.listByUnCheck(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()-1000*24*60*60)));
//        System.out.println(JSON.toJSONString(lists));
        lists.stream().forEach(this::checkForImg);
    }

    public void checkForImg(ResourceDTO resourceDTO){
        // 传图片url进行检测，name结构产品自行设计，用于唯一定位该图片数据
        Map<String,String> kvs = new HashMap<>();
        kvs.put("secretId",SECRET_ID);
        kvs.put("businessId",BUSINESS_ID);
        kvs.put("version","v3.2");
        kvs.put("timestamp",String.valueOf(System.currentTimeMillis()));
        kvs.put("nonce",String.valueOf(new Random().nextInt()));
        switch (resourceDTO.getResourceType()){
            case 1:{
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",String.valueOf(resourceDTO.getId()));
                jsonObject.put("type",resourceDTO.getResourceType());
                jsonObject.put("data",resourceDTO.getResourceObj());
                kvs.put("images","["+jsonObject.toJSONString()+"]");
                String signature = null;
                try {
                    signature = genSignature(SECRET_KEY,kvs);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                kvs.put("signature",signature);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.setAll(kvs);
                System.out.println(JSON.toJSONString(map));
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
                System.out.println(JSON.toJSONString(request));
                ResponseEntity response = restTemplate.postForEntity(API_URL,request,String.class);
                JSONObject resp = JSONObject.parseObject(response.getBody().toString());
                if (JSONObject.parseObject(JSON.parseArray(JSONObject.parseObject(JSON.parseArray(resp.getString("result")).getString(0)).getString("labels")).getString(0)).getString("level").equals(ReturnCode.NO)){
                    resourceDao.updateValidById(1,Long.valueOf(JSONObject.parseObject(JSON.parseArray(resp.getString("result")).getString(0)).getString("name")));
                } else {
                    resourceDao.updateValidById(0,Long.valueOf(JSONObject.parseObject(JSON.parseArray(resp.getString("result")).getString(0)).getString("name")));
                }
                break;
            }
        }
    }

    /**
     * 生成签名信息
     * @param secretKey 产品私钥
     * @param params 接口请求参数名和参数值map，不包括signature参数名
     * @return
     */
    public static String genSignature(String secretKey, Map<String, String> params)
        throws UnsupportedEncodingException {
        // 1. 参数名按照ASCII码表升序排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 2. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        // 3. 将secretKey拼接到最后
        sb.append(secretKey);

        // 4. MD5是128位长度的摘要算法，转换为十六进制之后长度为32字符
        return DigestUtils.md5Hex(sb.toString().getBytes("UTF-8"));
    }

}
