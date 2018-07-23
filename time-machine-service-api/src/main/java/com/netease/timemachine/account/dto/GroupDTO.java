package com.netease.timemachine.account.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wqh
 * @description: 管理用户与孩子的类  多对多
 * @Date: Created in 13:37 2018/7/20
 **/
@Data
@NoArgsConstructor
public class GroupDTO implements Serializable {

    private static final long serialVersionUID = 6765190487092183735L;

    private Long groupId;

    private Long childId;

    private Long userId;

    /**身份描述：爸爸，妈妈....*/
    private String identification;

    /**权限：管理：1，普通：0*/
    private Integer permission;

    public GroupDTO(Long childId, Long userId, String identification, Integer permission) {
        this.childId = childId;
        this.userId = userId;
        this.identification = identification;
        this.permission = permission;
    }
}
