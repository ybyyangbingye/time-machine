package com.netease.timemachine.meta;

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

    private Long groupId;

    private Long childId;

    private Long userId;

    /**权限：管理：1，普通：0*/
    private Integer permission;

    /**身份描述：爸爸，妈妈....*/
    private String identification;
}
