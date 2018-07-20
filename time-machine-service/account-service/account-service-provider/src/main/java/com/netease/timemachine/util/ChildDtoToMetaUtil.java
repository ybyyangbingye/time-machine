package com.netease.timemachine.util;

import com.netease.timemachine.dto.ChildDTO;
import com.netease.timemachine.meta.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 16:42 2018/7/18
 **/
public class ChildDtoToMetaUtil {
    /**
     * child集合转ChildDTO集合
     * @param children
     * @return
     */
    public static List<ChildDTO> childMetaToDTOList(List<Child> children){
        if(children == null) {
            return null;
        }
        List<ChildDTO> childDTOList = new ArrayList<>(children.size());
        for (Child child:children) {
            childDTOList.add(childMetaToDto(child));
        }
        return childDTOList;
    }

    public static Child childDtoToMeta(ChildDTO childDTO){
        if(childDTO == null){
            return null;
        }
        Child child = new Child();
        child.setChildId(childDTO.getChildId());
        child.setChildName(childDTO.getChildName());
        child.setGender(childDTO.getGender());
        child.setImgUrl(childDTO.getImgUrl());
        child.setBirthDate(childDTO.getBirthDate());
        return child;
    }

    public static ChildDTO childMetaToDto(Child child){
        if(child == null){
            return null;
        }
        ChildDTO childDTO = new ChildDTO();
        childDTO.setChildId(child.getChildId());
        childDTO.setChildName(child.getChildName());
        childDTO.setGender(child.getGender());
        childDTO.setBirthDate(child.getBirthDate());
        childDTO.setImgUrl(child.getImgUrl());
        return childDTO;
    }
}
