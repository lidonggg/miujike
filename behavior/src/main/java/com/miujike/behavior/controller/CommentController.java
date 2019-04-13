package com.miujike.behavior.controller;

import com.miujike.behavior.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ls J
 * @date 2019/4/13 12:34 PM
 */
@Controller
@RequestMapping("api/v1/behavior/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;
}
