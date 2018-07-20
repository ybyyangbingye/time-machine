package com.netease.timemachine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 20:10 2018/7/17
 **/
@Data
@NoArgsConstructor
public class ChildDTO implements Serializable {

    private static final long serialVersionUID = 3642317137445935926L;

    private Long childId;

    private Integer gender;

    private String childName;

    private String imgUrl;

    /**形如2018-07-17*/
    private String birthDate;

}
