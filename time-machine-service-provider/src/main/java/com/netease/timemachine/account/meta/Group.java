package com.netease.timemachine.account.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wqh
 * @description: 孩子和用户管理类
 * @Date: Created in 13:37 2018/7/20
 **/
@Data
@NoArgsConstructor
public class Group implements Serializable {

    private static final long serialVersionUID = -8345277924592070879L;

    /**childId和userId联合表征*/
    private Long childId;

    private Long userId;

    /**身份描述：爸爸，妈妈....*/
    private String identification;

    /**昵称，宝宝称呼长辈*/
    private String nickName;

    /**权限（创建者：0，管理者：1，记录：2，查看：3），默认为2记录*/
    private Integer permission;

    private String imgUrl;
}
