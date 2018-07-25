package com.netease.timemachine.moment.serviceImpl;

import com.netease.timemachine.moment.dao.CommentDao;
import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.meta.Comment;
import com.netease.timemachine.moment.service.CommentService;
import com.netease.timemachine.moment.util.CommentDtoToMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public void insertComment(CommentDTO commentDTO) {
        Comment comment = CommentDtoToMeta.commentDtoToMeta(commentDTO);
        comment.setCreateTime(new Date());
        commentDao.insertComment(comment);
    }

    @Override
    public List<CommentDTO> selectComments(Long momentId) {
        List<Comment> commentList = commentDao.selectComments(momentId);
        return CommentDtoToMeta.commentMetaToDtoList(commentList);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentDao.deleteComment(commentId);
    }
}
