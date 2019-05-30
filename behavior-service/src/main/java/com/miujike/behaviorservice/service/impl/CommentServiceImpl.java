package com.miujike.behaviorservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.behaviorservice.domain.Comment;
import com.miujike.behaviorservice.mapper.CommentMapper;
import com.miujike.behaviorservice.service.ICommentService;
import com.miujike.behaviorservice.service.IWorksClient;
import com.miujike.common.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/13 12:10 PM
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private IWorksClient worksClient;

    @Value("${fetchNum}")
    private int fetchNum;

    @Override
    public List<Map<String, Object>> getCommentList(int targetType, Long targetId, Long lastId) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("targetType", targetType);
        map.put("targetId", targetId);
        map.put("lastId", lastId);
        map.put("fetchNum", fetchNum);
        return commentMapper.getCommentList(map);
    }

    @Override
    public Comment addComment(Comment comment) {
        comment.setCreateTime(new Date());

        commentMapper.addComment(comment);
//        if (commentAdded.getCommentId() != 0L) {
            //TODO 添加评论数
            boolean s = worksClient.addCommentNum(comment.getTargetId(), comment.getTargetType());
            return s ? comment : null;
//        }
//        return null;
    }
}
