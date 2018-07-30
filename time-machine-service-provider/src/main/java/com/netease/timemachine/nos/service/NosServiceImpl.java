/**
 * @(#)NosServiceImpl.java, 2018/7/23.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.nos.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.nos.dto.NosDTO;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */
@Service
public class NosServiceImpl implements NosService{

    private static final String accessKey = "0f9e2b8694a64d83bf42ef433ddd13fb";
    private static final String accessSecret = "573f871271f3476d88e857e91e5bd199";


    @Override public String create(NosDTO nosDTO) {
        System.out.println(JSON.toJSONString(nosDTO));
        JSONObject putPolicy = new JSONObject();
        putPolicy.put("Bucket", nosDTO.getBucket());
        putPolicy.put("Object", nosDTO.getObject());
        putPolicy.put("Expires", nosDTO.getExpires());
        String encodedPutPolicy  =new String(Base64.encodeBase64(putPolicy.toString().getBytes()));
        String token = null;
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(accessSecret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String rs = Base64.encodeBase64String(sha256_HMAC.doFinal(encodedPutPolicy .getBytes()));
            token = "UPLOAD " + accessKey + ":" + rs + ":" + encodedPutPolicy ;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return token;

    }

    public static void main(String[] args){
        NosDTO nosDTO = new NosDTO();
        nosDTO.setBucket("doc");
        nosDTO.setObject("anne.jpg");
        nosDTO.setExpires(1451491200);
        System.out.println(new NosServiceImpl().create(nosDTO));
    }
}
