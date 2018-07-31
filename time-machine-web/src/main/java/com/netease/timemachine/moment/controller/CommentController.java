package com.netease.timemachine.moment.controller;

import com.netease.timemachine.account.dto.GroupDTO;
import com.netease.timemachine.account.service.GroupService;
import com.netease.timemachine.account.util.ResponseView;
import com.netease.timemachine.moment.dto.CommentDTO;
import com.netease.timemachine.moment.service.CommentService;
import com.netease.timemachine.moment.util.CommentVoToDto;
import com.netease.timemachine.moment.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/insert")
    public ResponseEntity insertComment(@RequestBody CommentVO commentVO){
        if(StringUtils.isEmpty(commentVO.getContent())){
            return ResponseView.fail(COMMENT_NULL.getCode(),COMMENT_NULL.getMessage());
        }
        CommentDTO commentDTO = CommentVoToDto.commentVoToDto(commentVO);
        commentService.insertComment(commentDTO);
        return ResponseView.success(null, "发表评论成功");
    }

    @RequestMapping("/allComments")
    public ResponseEntity selectComments(@RequestParam("childId") Long childId,
                                         @RequestParam("momentId") Long momentId){
        List<CommentDTO> commentDTOList = commentService.selectComments(momentId);
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

    @RequestMapping("/delete")
    public ResponseEntity deleteComment(Long commentId){
        commentService.deleteComment(commentId);
        return ResponseView.success(null, "删除成功");
    }
}
