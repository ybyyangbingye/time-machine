package com.netease.timemachine.momment.util;

import com.netease.timemachine.momment.dto.MommentDTO;
import com.netease.timemachine.momment.meta.Momment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WYT
 * @Description: 说说DTO转Meta
 * @Date: 2018/7/24 14:07
 */

public class MommentDtoToMeta {

    /**
     *
     * @param momments
     * @return
     */
    public static List<MommentDTO> metaToDtoList(List<Momment> momments) {
        if(momments == null) {
            return null;
        }
        List<MommentDTO> res = new ArrayList<>();
        for(Momment momment : momments) {
            res.add(metaToDto(momment));
        }
        return res;
    }

    /**
     * momment list转 mommentDTO list
     * @param momment
     * @return
     */
    public static MommentDTO metaToDto(Momment momment) {
        if(momment == null) {
            return null;
        }
        MommentDTO mommentDto = new MommentDTO();
        mommentDto.setMomment_id(momment.getMomment_id());
        mommentDto.setCreator_id(momment.getCreator_id());
        mommentDto.setDescription(momment.getDescription());
        mommentDto.setTag_id(momment.getTag_id());
        mommentDto.setChild_id(momment.getChild_id());
        mommentDto.setGmt_create(momment.getGmt_create());
        mommentDto.setGmt_modified(momment.getGmt_modified());
        return mommentDto;
    }


    /**
     *
     * @param mommentDTO
     * @return
     */
    public static Momment dtoToMeta(MommentDTO mommentDTO) {
        if(mommentDTO == null) {
            return null;
        }
        Momment momment = new Momment();
        momment.setMomment_id(mommentDTO.getMomment_id());
        momment.setCreator_id(mommentDTO.getCreator_id());
        momment.setDescription(mommentDTO.getDescription());
        momment.setTag_id(mommentDTO.getTag_id());
        momment.setChild_id(mommentDTO.getChild_id());
        momment.setGmt_create(mommentDTO.getGmt_create());
        momment.setGmt_modified(mommentDTO.getGmt_modified());
        return momment;
    }

}
