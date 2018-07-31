package com.netease.timemachine.moment.controller;

import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.meta.Comment;
import com.netease.timemachine.moment.service.CommentService;
import com.netease.timemachine.moment.util.CommentVoToDto;
import com.netease.timemachine.moment.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.netease.timemachine.moment.enums.CommentEnum.COMMENT_NULL;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 10:29 2018/7/25
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity insertComment(@RequestBody CommentVO commentVO){
        if(StringUtils.isEmpty(commentVO.getContent())){
            return ResponseView.fail(COMMENT_NULL.getCode(),COMMENT_NULL.getMessage());
        }
        CommentDTO commentDTO = CommentVoToDto.commentVoToDto(commentVO);
        Long commentId = commentService.insertComment(commentDTO);
        /**前端直接插入该条评论，无需刷新首页*/
        commentVO.setCommentId(commentId);
        commentVO.setCreateTime(commentService.selectByCommentId(commentId).getCreateTime());
        GroupDTO parent = groupService.selectByUserAndChildId(commentVO.getParentId(), commentVO.getChildId());
        commentVO.setParentNickName(parent.getNickName());
        GroupDTO reply = groupService.selectByUserAndChildId(commentVO.getReplyId(), commentVO.getChildId());
        commentVO.setReplyNickName(reply.getNickName());
        return ResponseView.success(commentVO, "发表评论成功");
    }

    /**
     * 目前不用
     * @param childId
     * @param momentId
     * @return
     */
    @RequestMapping("/allComments")
    public ResponseEntity selectComments(@RequestParam("childId") Long childId,
                                         @RequestParam("momentId") Long momentId){
        List<CommentDTO> commentDTOList = commentService.selectComments(childId,momentId);
        List<CommentVO> commentVOList = CommentVoToDto.commentDtoToVoList(commentDTOList);
        List<CommentVO> commentList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(commentVOList)){
            for (CommentVO commentVO : commentVOList){
                GroupDTO parent = groupService.selectByUserAndChildId(commentVO.getParentId(), childId);
                commentVO.setParentNickName(parent.getNickName());
                GroupDTO reply = groupService.selectByUserAndChildId(commentVO.getReplyId(), childId);
                commentVO.setReplyNickName(reply.getNickName());
                commentList.add(commentVO);
            }
        }
        return ResponseView.success(commentList);
    }

    /**
     * 测试用
     * @param childId
     * @param momentId
     * @return
     */
    @RequestMapping("/allComments1")
    public ResponseEntity selectComments1(@RequestParam("childId") Long childId,
                                         @RequestParam("momentId") Long momentId){
        List<CommentDTO> commentDTOList = commentService.selectComments(childId,momentId);
        return ResponseView.success(CommentVoToDto.commentDtoToVoList(commentDTOList));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity deleteComment(@RequestParam Long commentId){
        commentService.deleteComment(commentId);
        return ResponseView.success(null, "删除成功");
    }
}
