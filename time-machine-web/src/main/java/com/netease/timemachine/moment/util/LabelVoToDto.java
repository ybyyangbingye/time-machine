package com.netease.timemachine.moment.util;

import com.netease.timemachine.moment.dto.LabelDTO;
import com.netease.timemachine.moment.vo.LabelVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/25 19:16
 */

public class LabelVoToDto {
    public static List<LabelVO> dtoListToVoList(List<LabelDTO> labels) {
        if(labels == null) {
            return null;
        }
        List<LabelVO> res = new ArrayList<>();
        for(LabelDTO label : labels) {
            res.add(dtoToVo(label));
        }
        return res;
    }

    public static List<LabelDTO> voListToDtoList(List<LabelVO> labels) {
        if(labels == null) {
            return null;
        }
        List<LabelDTO> res = new ArrayList<>();
        for(LabelVO label : labels) {
            res.add(voToDto(label));
        }
        return res;
    }

    public static LabelDTO voToDto(LabelVO labelVO) {
        if(labelVO == null) {
            return null;
        }
        LabelDTO labelDTO = new LabelDTO();
        labelDTO.setLabelId(labelVO.getLabelId());
        labelDTO.setLabelName(labelVO.getLabelName());
        labelDTO.setUserId(labelVO.getUserId());
        labelDTO.setGmtCreate(labelVO.getGmtCreate());
        labelDTO.setGmtModified(labelVO.getGmtCreate());
        return labelDTO;
    }



    public static LabelVO dtoToVo(LabelDTO labelDTO) {
        if(labelDTO == null) {
            return null;
        }
        LabelVO label = new LabelVO();
        label.setLabelId(labelDTO.getLabelId());
        label.setLabelName(labelDTO.getLabelName());
        label.setUserId(labelDTO.getUserId());
        label.setGmtCreate(labelDTO.getGmtCreate());
        label.setGmtModified(labelDTO.getGmtCreate());
        return label;
    }

}
