package com.netease.timemachine.moment.util;

import com.netease.timemachine.moment.dto.MomentDTO;
import com.netease.timemachine.moment.vo.MomentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WYT
 * @Description:
 * @Date: 2018/7/24 14:39
 */

public class MomentVoToDto {

    /**
     *
     * @param moments
     * @return
     */
    public static List<MomentVO> dtoListToVoList(List<MomentDTO> moments) {
        if(moments == null) {
            return null;
        }
        List<MomentVO> res = new ArrayList<>();
        for(MomentDTO dto : moments) {
            res.add(dtoToVo(dto));
        }
        return res;
    }

    /**
     *
     * @param dto
     * @return
     */
    public static MomentVO dtoToVo(MomentDTO dto) {
        if(dto == null) {
            return null;
        }
        MomentVO momentVO = new MomentVO();
        momentVO.setMomentId(dto.getMomentId());
        momentVO.setCreatorId(dto.getCreatorId());
        momentVO.setTile(dto.getTitle());
        momentVO.setGroupType(dto.getGroupType());
        momentVO.setDescription(dto.getDescription());
        momentVO.setLocation(dto.getLocation());
        momentVO.setChildId(dto.getChildId());
        momentVO.setGmtCreate(dto.getGmtCreate());
        momentVO.setGmtModified(dto.getGmtModified());
        return momentVO;
    }


    public static MomentDTO voToDto(MomentVO vo) {
        if(vo == null) {
            return null;
        }
        MomentDTO dto = new MomentDTO();
        dto.setMomentId(vo.getMomentId());
        dto.setCreatorId(vo.getCreatorId());
        dto.setTitle(vo.getTile());
        dto.setGroupType(vo.getGroupType());
        dto.setDescription(vo.getDescription());
        dto.setLocation(vo.getLocation());
        dto.setChildId(vo.getChildId());
        dto.setGmtCreate(vo.getGmtCreate());
        dto.setGmtModified(vo.getGmtModified());
        return dto;
    }
}
