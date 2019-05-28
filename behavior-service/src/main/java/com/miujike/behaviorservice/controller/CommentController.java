package com.miujike.behaviorservice.controller;

import com.miujike.behaviorservice.domain.Comment;
import com.miujike.behaviorservice.service.ICommentService;
import com.miujike.common.constants.BaseController;
import com.miujike.common.constants.ResponseConstants;
import com.miujike.common.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ls J
 * @date 2019/4/13 12:34 PM
 */
@RestController
@RequestMapping("api/v1/behavior/comment")
public class CommentController extends BaseController {

    @Autowired
    private ICommentService commentService;

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @PostMapping("addComment")
    public ResponseData addComment(Comment comment) {
        log.info("=======>{}", comment.toString());
        Comment commentAdded = commentService.addComment(comment);
        return null != commentAdded ? new ResponseData<>(commentAdded) : new ResponseData<>(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL, -1);
    }

    /**
     * 拉取评论列表
     *
     * @param targetId
     * @param targetType
     * @return
     */
    @GetMapping("list/{targetId}")
    public ResponseData listComment(@PathVariable Long targetId, int targetType, Long lastId) {
        return new ResponseData<>(commentService.getCommentList(targetType, targetId, lastId));
    }

}
