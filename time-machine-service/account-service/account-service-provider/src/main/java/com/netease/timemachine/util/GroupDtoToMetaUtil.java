package com.netease.timemachine.util;

import com.netease.timemachine.dao.GroupDao;
import com.netease.timemachine.dto.GroupDTO;
import com.netease.timemachine.meta.Group;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 15:12 2018/7/20
 **/
public class GroupDtoToMetaUtil {

    public static Group GroupDtoToMeta(GroupDTO groupDTO){
        Group group = new Group();
        group.setUserId(groupDTO.getUserId());
        group.setChildId(groupDTO.getChildId());
        group.setUserId(groupDTO.getUserId());
        group.setIdentification(groupDTO.getIdentification());
        group.setPermission(groupDTO.getPermission());
        return group;
    }

    public static GroupDTO GroupMetaToDTO(Group group){
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupId(group.getGroupId());
        groupDTO.setChildId(group.getChildId());
        groupDTO.setUserId(group.getUserId());
        groupDTO.setIdentification(group.getIdentification());
        groupDTO.setPermission(group.getPermission());
        return groupDTO;
    }
}
