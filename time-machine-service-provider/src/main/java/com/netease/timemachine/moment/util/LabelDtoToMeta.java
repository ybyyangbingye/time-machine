package com.netease.timemachine.moment.util;

import com.netease.timemachine.moment.dto.LabelDTO;
import com.netease.timemachine.moment.meta.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 19:15
 */

public class LabelDtoToMeta {

    public static List<LabelDTO> metaListToDtoList(List<Label> labels) {
        if(labels == null) {
            return null;
        }
        List<LabelDTO> res = new ArrayList<>();
        for(Label label : labels) {
            res.add(metaToDto(label));
        }
        return res;
    }


    public static LabelDTO metaToDto(Label label) {
        if(label == null) {
            return null;
        }
        LabelDTO labelDTO = new LabelDTO();
        labelDTO.setLabelId(label.getLabelId());
        labelDTO.setGroupId(label.getGroupId());
        labelDTO.setLabelName(label.getLabelName());
        labelDTO.setLabelType(label.getLabelType());
        labelDTO.setUserId(label.getUserId());
        labelDTO.setGmtCreate(label.getGmtCreate());
        labelDTO.setGmtModified(label.getGmtCreate());
        return labelDTO;
    }



    public static Label dtoToMeta(LabelDTO labelDTO) {
        if(labelDTO == null) {
            return null;
        }
        Label label = new Label();
        label.setLabelId(labelDTO.getLabelId());
        label.setGroupId(labelDTO.getGroupId());
        label.setLabelName(labelDTO.getLabelName());
        label.setLabelType(labelDTO.getLabelType());
        label.setUserId(labelDTO.getUserId());
        label.setGmtCreate(labelDTO.getGmtCreate());
        label.setGmtModified(labelDTO.getGmtCreate());
        return label;
    }

}
