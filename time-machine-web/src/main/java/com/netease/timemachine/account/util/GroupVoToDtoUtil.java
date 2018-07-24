package com.netease.timemachine.account.util;

import com.netease.timemachine.account.dto.ChildDTO;
import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.meta.Group;
import com.netease.timemachine.account.vo.ChildVO;
import com.netease.timemachine.account.vo.GroupVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 19:52 2018/7/23
 **/
public class GroupVoToDtoUtil {

    public static GroupVO GroupDtoToVo(GroupDTO groupDTO){
        if(groupDTO == null){
            return null;
        }
        GroupVO groupVO = new GroupVO();
        groupVO.setChildId(groupDTO.getChildId());
        groupVO.setUserId(groupDTO.getUserId());
        groupVO.setIdentification(groupDTO.getIdentification());
        groupVO.setNickName(groupDTO.getNickName());
        groupVO.setPermission(groupDTO.getPermission());
        groupVO.setImgUrl(groupDTO.getImgUrl());
        return groupVO;
    }

    public static GroupDTO GroupVoToDto(GroupVO GroupVO){
        if(GroupVO == null){
            return null;
        }
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setChildId(GroupVO.getChildId());
        groupDTO.setUserId(GroupVO.getUserId());
        groupDTO.setIdentification(GroupVO.getIdentification());
        groupDTO.setNickName(GroupVO.getNickName());
        groupDTO.setPermission(GroupVO.getPermission());
        groupDTO.setImgUrl(GroupVO.getImgUrl());
        return groupDTO;
    }

    public static List<GroupVO> GroupDtoToVoList(List<GroupDTO> groupDTOList){
        if(groupDTOList == null){
            return null;
        }
        List<GroupVO> groupVOList = new ArrayList<GroupVO>(groupDTOList.size());
        for (GroupDTO groupDTO:groupDTOList) {
            groupVOList.add(GroupDtoToVo(groupDTO));
        }
        return groupVOList;
    }

}
