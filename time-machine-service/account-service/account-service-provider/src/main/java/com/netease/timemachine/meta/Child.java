package com.netease.timemachine.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 19:20 2018/7/17
 **/
@Data
@NoArgsConstructor
public class Child implements Serializable {

    private static final long serialVersionUID = 3642317137445935926L;

    private Long childId;

    private Integer gender;

    private String childName;

    private String imgUrl;

    /**形如2018-07-17*/
    private String birthDate;

    /**孩子所属管理者Id，两个的话用-进行串联*/
    private String managerId;

    public Child(Integer gender, String childName, String imgUrl, String birthDate, String managerId) {
        this.gender = gender;
        this.childName = childName;
        this.imgUrl = imgUrl;
        this.birthDate = birthDate;
        this.managerId = managerId;
    }
}
