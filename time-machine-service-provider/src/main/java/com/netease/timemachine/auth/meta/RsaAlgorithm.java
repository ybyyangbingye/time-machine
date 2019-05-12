/**
 * @(#)Algorithm.java, 2018/7/19.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.timemachine.auth.meta;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author 李育鑫(liyuxin02 @ corp.netease.com)
 */

@Component
public class RsaAlgorithm{

    private Algorithm algorithm =null;

    public RsaAlgorithm() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        algorithm = Algorithm.RSA256(publicKey, privateKey);
    }

    public String create(Map<String, Object> headerMap, Map<String, Object> playloadMap) {
        Class c = null;
        try {
            c = Class.forName("com.auth0.jwt.JWTCreator");
            Constructor con = c.getDeclaredConstructor(Algorithm.class, Map.class, Map.class);
            con.setAccessible(true);
            JWTCreator jwtCreator = (JWTCreator) con.newInstance(algorithm, headerMap, playloadMap);
            Method method = c.getDeclaredMethod("sign");
            method.setAccessible(true);
            String token = (String) method.invoke(jwtCreator);
            return token;
        } catch (Exception e) {
            throw new JWTCreationException("Can't create JWT string", e);
        }
    }

    public void verify(String token) throws JWTDecodeException {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new JWTDecodeException("JWT format is not standard");
        } else {
            String str = parts[0] + "." + parts[1];
            byte[] signatureBytes = algorithm.sign(str.getBytes(StandardCharsets.UTF_8));
            String signature = Base64.encodeBase64URLSafeString(signatureBytes);
            if (!signature.equals(parts[2])) {
                throw new JWTDecodeException("The token is of wrong format");
            }
        }
    }
}
