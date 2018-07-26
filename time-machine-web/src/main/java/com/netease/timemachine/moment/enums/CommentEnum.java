package com.netease.timemachine.moment.enums;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:35 2018/7/25
 **/
public enum  CommentEnum {

    /**评论为空*/
    COMMENT_NULL(100, "评论不能为空");

    CommentEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
