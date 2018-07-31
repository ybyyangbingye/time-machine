package com.netease.timemachine.moment.util;

import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.vo.CommentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:49 2018/7/25
 **/
public class CommentVoToDto {

    public static CommentDTO commentVoToDto(CommentVO commentVO) {
        if (commentVO == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(commentVO.getCommentId());
        commentDTO.setMomentId(commentVO.getMomentId());
        commentDTO.setContent(commentVO.getContent());
        commentDTO.setParentId(commentVO.getParentId());
        commentDTO.setParentNickName(commentVO.getParentNickName());
        commentDTO.setReplyId(commentVO.getReplyId());
        commentDTO.setReplyNickName(commentVO.getReplyNickName());
        commentDTO.setCreateTime(commentVO.getCreateTime());
        return commentDTO;
    }

    public static CommentVO commentDtoToVo(CommentDTO commentDTO) {
        if (commentDTO == null) {
            return null;
        }
        CommentVO commentVO = new CommentVO();
        commentVO.setCommentId(commentDTO.getCommentId());
        commentVO.setContent(commentDTO.getContent());
        commentVO.setMomentId(commentDTO.getMomentId());
        commentVO.setParentId(commentDTO.getParentId());
        commentVO.setParentNickName(commentDTO.getParentNickName());
        commentVO.setReplyId(commentDTO.getReplyId());
        commentVO.setReplyNickName(commentDTO.getReplyNickName());
        commentVO.setCreateTime(commentDTO.getCreateTime());
        return commentVO;
    }

    public static List<CommentVO> commentDtoToVoList(List<CommentDTO> commentDTOList) {
        if (commentDTOList == null) {
            return null;
        }
        List<CommentVO> commentVOList = new ArrayList<>(commentDTOList.size());
        for (CommentDTO commentDTO : commentDTOList) {
            CommentVO CommentVO = commentDtoToVo(commentDTO);
            commentVOList.add(CommentVO);
        }
        return commentVOList;
    }
}
