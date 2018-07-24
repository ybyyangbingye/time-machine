package com.netease.timemachine.account.meta;

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

    public Child(Integer gender, String childName, String imgUrl, String birthDate) {
        this.gender = gender;
        this.childName = childName;
        this.imgUrl = imgUrl;
        this.birthDate = birthDate;
    }
}
