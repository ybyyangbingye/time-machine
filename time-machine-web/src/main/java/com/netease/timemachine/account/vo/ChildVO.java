package com.netease.timemachine.account.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:19 2018/7/18
 **/
@Data
@NoArgsConstructor
public class ChildVO implements Serializable {

    private static final long serialVersionUID = -8372910816593512352L;

    private Long childId;

    private Integer gender;

    private String childName;

    private String imgUrl;

    private Date birthDate;

    /**在插入group表时需要使用userId，通过ChildVO这个对象传递*/
    private Long userId;

    /**同上，插入表时用*/
    private String identification;

    /**获取孩子当前生日多少天，规则：
     * 从出生到1个月：第1天、第2天、第3天。。。
     * 从第1个月到1周岁：3个月，3个月1天，3个月2天。。。
     * 1周岁以后：1周岁，1岁1天。。1岁1个月，1岁1个月1天，1岁1个月2天。。
     */
    private String age;

}
