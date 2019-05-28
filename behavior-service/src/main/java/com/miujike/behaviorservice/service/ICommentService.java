package com.miujike.behaviorservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.behaviorservice.domain.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/13 12:10 PM
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 拉取评论列表
     *
     * @param targetType targetType
     * @param targetId   targetId
     * @param lastId     lastId
     * @return list
     */
    List<Map<String, Object>> getCommentList(int targetType, Long targetId, Long lastId);

    /**
     * 添加评论
     *
     * @param comment
     * @return  comment
     */
    Comment addComment(Comment comment);
}
