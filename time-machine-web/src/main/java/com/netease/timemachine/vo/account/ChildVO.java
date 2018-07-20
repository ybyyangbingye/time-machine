package com.netease.timemachine.vo.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    /**形如2018-07-17*/
    private String birthDate;

    /**在插入group表时需要使用userId，通过ChildVO这个对象传递*/
    private Long userId;
}
