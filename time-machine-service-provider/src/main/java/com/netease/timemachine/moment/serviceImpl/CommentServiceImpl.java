package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.account.dao.GroupDao;
import com.netease.timemachine.account.meta.Group;
import com.netease.timemachine.moment.dao.CommentDao;
import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.meta.Comment;
import com.netease.timemachine.moment.service.CommentService;
import com.netease.timemachine.moment.util.CommentDtoToMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 9:42 2018/7/25
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private GroupDao groupDao;

    @Override
    public Long insertComment(CommentDTO commentDTO) {
        Comment comment = CommentDtoToMeta.commentDtoToMeta(commentDTO);
        commentDao.insertComment(comment);
        return comment.getCommentId();
    }

    @Override
    public List<CommentDTO> selectComments(Long childId, Long groupId) {
        List<Comment> commentList = commentDao.selectComments(groupId);
        List<CommentDTO> res = new ArrayList<>();
        List<CommentDTO>  commentDTOList = CommentDtoToMeta.commentMetaToDtoList(commentList);
        if(!CollectionUtils.isEmpty(commentDTOList)){
            for (CommentDTO commentDTO : commentDTOList){
                Group parent = groupDao.selectByUserAndChildId(commentDTO.getParentId(), childId);
                if(parent != null) {
                    commentDTO.setParentNickName(parent.getNickName());
                }
                Group reply = groupDao.selectByUserAndChildId(commentDTO.getReplyId(), childId);
                if(reply != null) {
                    commentDTO.setReplyNickName(reply.getNickName());
                }
                res.add(commentDTO);
            }
        }
        return res;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentDao.deleteComment(commentId);
    }

    @Override
    public int getCommentCountByGroupIdType(Long groupId) {
        return commentDao.getCommentCountByGroupIdType(groupId);
    }

    @Override
    public CommentDTO selectByCommentIdType(Long commentId) {
        Comment comment = commentDao.selectByCommentIdType(commentId);
        return CommentDtoToMeta.commentMetaToDto(comment);
    }
}
