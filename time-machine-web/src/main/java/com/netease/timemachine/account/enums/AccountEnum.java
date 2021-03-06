package com.netease.timemachine.account.enums;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:43 2018/8/4
 **/
public enum AccountEnum {

    /**定义枚举异常*/
    INNER_ERROR(1000, "内部错误"),
    CHILD_NULL(1001, "没有该宝宝信息"),
    BINED_REPEAT(1002, "已关注该宝宝"),
    APPLY_REPEAT(1003, "该宝宝已经申请过，请等待确认"),
    USER_NULL(1004, "该用户未注册"),
    CHILD_REPEAT(1005, "已创建宝宝"),
    BIND_FAILED(1006, "绑定失败"),
    SEND_FAILED(1007, "发送失败"),
    VERIFY_FAILED(1008, "验证失败"),
    PICCODE_FAILED(1009, "图形验证码验证失败");


    private Integer code;

    private String message;

    AccountEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
