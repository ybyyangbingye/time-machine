package com.netease.timemachine.moment.util;

import com.netease.timemachine.moment.dto.MomentDTO;
import com.netease.timemachine.moment.meta.Moment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WYT
 * @Description: 说说DTO转Meta
 * @Date: 2018/7/24 14:07
 */

public class MomentDtoToMeta {

    /**
     *
     * @param moments
     * @return
     */
    public static List<MomentDTO> metaListToDtoList(List<Moment> moments) {
        if(moments == null) {
            return null;
        }
        List<MomentDTO> res = new ArrayList<>();
        for(Moment moment : moments) {
            res.add(metaToDto(moment));
        }
        return res;
    }

    /**
     * moment list转 momentDTO list
     * @param moment
     * @return
     */
    public static MomentDTO metaToDto(Moment moment) {
        if(moment == null) {
            return null;
        }
        MomentDTO momentDto = new MomentDTO();
        momentDto.setMomentId(moment.getMomentId());
        momentDto.setCreatorId(moment.getCreatorId());
        momentDto.setTitle(moment.getTitle());
        momentDto.setGroupType(moment.getGroupType());
        momentDto.setDescription(moment.getDescription());
        momentDto.setLocation(moment.getLocation());
        momentDto.setChildId(moment.getChildId());
        momentDto.setGmtCreate(moment.getGmtCreate());
        momentDto.setGmtModified(moment.getGmtModified());
        momentDto.setResourceType(moment.getResourceType());
        return momentDto;
    }


    /**
     *
     * @param momentDTO
     * @return
     */
    public static Moment dtoToMeta(MomentDTO momentDTO) {
        if(momentDTO == null) {
            return null;
        }
        Moment moment = new Moment();
        moment.setMomentId(momentDTO.getMomentId());
        moment.setCreatorId(momentDTO.getCreatorId());
        moment.setTitle(momentDTO.getTitle());
        moment.setGroupType(momentDTO.getGroupType());
        moment.setDescription(momentDTO.getDescription());
        moment.setLocation(momentDTO.getLocation());
        moment.setChildId(momentDTO.getChildId());
        moment.setGmtCreate(momentDTO.getGmtCreate());
        moment.setGmtModified(momentDTO.getGmtModified());
        moment.setResourceType(momentDTO.getResourceType());
        return moment;
    }

}
