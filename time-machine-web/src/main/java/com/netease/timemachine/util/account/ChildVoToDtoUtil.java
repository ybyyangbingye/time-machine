package com.netease.timemachine.util.account;

import com.netease.timemachine.dto.ChildDTO;
import com.netease.timemachine.vo.account.ChildVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 16:51 2018/7/18
 **/
public class ChildVoToDtoUtil {



    /**
     * ChildDTO集合转ChildVO集合
     * @param childDTOList
     * @return
     */
    public static List<ChildVO> childDtoToVoList(List<ChildDTO> childDTOList){
        if(childDTOList == null){
            return null;
        }
        List<ChildVO> childVOList = new ArrayList<>(childDTOList.size());
        for (ChildDTO childDTO:childDTOList) {
            childVOList.add(childDtoToVo(childDTO));
        }
        return childVOList;
    }

    public static ChildDTO childVoToDto(ChildVO childVO){
        if(childVO == null){
            return null;
        }
        ChildDTO childDTO = new ChildDTO();
        childDTO.setChildId(childVO.getChildId());
        childDTO.setChildName(childVO.getChildName());
        childDTO.setBirthDate(childVO.getBirthDate());
        childDTO.setGender(childVO.getGender());
        childDTO.setImgUrl(childVO.getImgUrl());
        return childDTO;
    }

    public static ChildVO childDtoToVo(ChildDTO childDTO){
        if(childDTO == null){
            return null;
        }
        ChildVO childVO = new ChildVO();
        childVO.setChildId(childDTO.getChildId());
        childVO.setChildName(childDTO.getChildName());
        childVO.setGender(childDTO.getGender());
        childVO.setBirthDate(childDTO.getBirthDate());
        childVO.setImgUrl(childDTO.getImgUrl());
        return childVO;
    }
}
