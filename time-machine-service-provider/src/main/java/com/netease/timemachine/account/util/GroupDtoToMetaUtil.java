package com.netease.timemachine.account.util;

import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.meta.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:12 2018/7/20
 **/
public class GroupDtoToMetaUtil {

    public static Group GroupDtoToMeta(GroupDTO groupDTO){
        if(groupDTO == null) {
            return null;
        }
        Group group = new Group();
        group.setChildId(groupDTO.getChildId());
        group.setUserId(groupDTO.getUserId());
        group.setIdentification(groupDTO.getIdentification());
        group.setPermission(groupDTO.getPermission());
        group.setNickName(groupDTO.getNickName());
        group.setImgUrl(groupDTO.getImgUrl());
        return group;
    }

    public static GroupDTO GroupMetaToDTO(Group group){
        if(group == null){
            return null;
        }
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setChildId(group.getChildId());
        groupDTO.setUserId(group.getUserId());
        groupDTO.setIdentification(group.getIdentification());
        groupDTO.setPermission(group.getPermission());
        groupDTO.setNickName(group.getNickName());
        groupDTO.setImgUrl(group.getImgUrl());
        return groupDTO;
    }

    public static List<GroupDTO> GroupMetaToDtoList(List<Group> groupList){
        if(groupList == null){
            return null;
        }
        List<GroupDTO> groupDTOList = new ArrayList<>(groupList.size());
        for(Group group : groupList){
            GroupDTO groupDTO = GroupMetaToDTO(group);
            groupDTOList.add(groupDTO);
        }
        return groupDTOList;
    }


}
