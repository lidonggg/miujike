package com.miujike.behaviorservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.behaviorservice.domain.Comment;
import com.miujike.behaviorservice.mapper.CommentMapper;
import com.miujike.behaviorservice.service.ICommentService;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/4/13 12:10 PM
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
}
