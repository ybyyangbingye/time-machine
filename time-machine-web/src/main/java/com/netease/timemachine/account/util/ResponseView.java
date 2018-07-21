package com.netease.timemachine.account.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:30 2018/7/18
 **/
public final class ResponseView {
    private static final String KEY_CODE = "code";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_RESULT = "result";
    private static final Integer CODE_SUCCESS = 200;

    private ResponseView() {
    }

    private static ResponseEntity<?> buildView(Object resultObject, Integer code, String message) {
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put(KEY_CODE, code);
        resultMap.put(KEY_MESSAGE, message);
        resultMap.put(KEY_RESULT, resultObject);
        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

    /**
     * 返回成功操作.
     *
     * @param resultObject 返回结果.
     * @return
     */
    public static ResponseEntity<?> success(Object resultObject) {
        return success(resultObject, "");
    }

    /**
     * 返回成功操作.
     *
     * @param resultObject 返回结果.
     * @param message      描述信息.
     * @return
     */
    public static ResponseEntity<?> success(Object resultObject, String message) {
        return buildView(resultObject, CODE_SUCCESS, message);
    }

    /**
     * 返回失败操作.
     *
     * @param code    错误码.
     * @param message 错误信息.
     * @return
     */
    public static ResponseEntity<?> fail(Integer code, String message) {
        return buildView(null, code, message);
    }

    /**
     * 返回失败操作.
     *
     * @param code         错误码.
     * @param message      错误信息.
     * @param resultObject 错误结果.
     * @return
     */
    public static ResponseEntity<?> fail(Integer code, String message, Object resultObject) {
        return buildView(resultObject, code, message);
    }
}
