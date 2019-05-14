package com.miujike.behaviorservice.controller;

import com.miujike.behaviorservice.domain.Comment;
import com.miujike.behaviorservice.service.ICommentService;
import com.miujike.common.constants.ResponseConstants;
import com.miujike.common.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 12:34 PM
 */
@RestController
@RequestMapping("api/v1/behavior/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("addComment")
    public ResponseData<Integer> addComment(Comment comment) {
        comment.setCreateTime(new Date());

        boolean success = commentService.save(comment);
        if (success) {
            //TODO 添加评论数

            return new ResponseData<>(1);
        }
        return new ResponseData<>(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL, -1);
    }

}
