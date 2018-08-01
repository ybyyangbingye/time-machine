package com.netease.timemachine.moment.util;

import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.meta.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 23:24 2018/7/24
 **/
public class CommentDtoToMeta {

    public static Comment commentDtoToMeta(CommentDTO commentDTO){
        if(commentDTO == null){
            return null;
        }
        Comment comment = new Comment();
        comment.setCommentId(commentDTO.getCommentId());
        comment.setGroupId(commentDTO.getGroupId());
        comment.setGroupType(commentDTO.getGroupType());
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setReplyId(commentDTO.getReplyId());
        comment.setCreateTime(commentDTO.getCreateTime());
        return comment;
    }

    public static CommentDTO commentMetaToDto(Comment comment){
        if(comment == null){
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setGroupId(comment.getGroupId());
        commentDTO.setGroupType(comment.getGroupType());
        commentDTO.setParentId(comment.getParentId());
        commentDTO.setReplyId(comment.getReplyId());
        commentDTO.setCreateTime(comment.getCreateTime());
        return commentDTO;
    }

    public static List<CommentDTO> commentMetaToDtoList(List<Comment> commentList){
        if(commentList == null){
            return null;
        }
        List<CommentDTO> commentDTOList = new ArrayList<>(commentList.size());
        for(Comment comment:commentList){
            CommentDTO commentDTO = commentMetaToDto(comment);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
