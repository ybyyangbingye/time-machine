package com.netease.timemachine.account.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 19:39 2018/7/23
 **/
@Data
public class GroupVO implements Serializable {

    private static final long serialVersionUID = -6201048857403087756L;

    private Long childId;

    private Long userId;

    /**身份描述：爸爸，妈妈....*/
    private String identification;

    /**昵称*/
    private String nickName;

    /**权限（创建者：0，爸爸妈妈：1，其他人：2）*/
    private Integer permission;

    /**展示图片地址*/
    private String imgUrl;
}
