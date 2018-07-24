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

    private Long childId;

    private Long userId;

    /**身份描述：爸爸，妈妈....*/
    private String identification;

    /**昵称*/
    private String nickName;

    /**权限（创建者：0，爸爸妈妈：1，其他人：2）*/
    private Integer permission;

    private String imgUrl;

    public GroupDTO(Long childId, Long userId, String identification, String nickName, Integer permission, String imgUrl) {
        this.childId = childId;
        this.userId = userId;
        this.identification = identification;
        this.nickName = nickName;
        this.permission = permission;
        this.imgUrl = imgUrl;
    }
}
