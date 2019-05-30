package com.miujike.behaviorservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miujike.behaviorservice.domain.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/13 12:09 PM
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 拉取评论列表
     *
     * @param map targetType,targetId,lastId
     * @return list
     */
    List<Map<String, Object>> getCommentList(Map<String, Object> map);

    /**
     * 插入评论，返回主键
     *
     * @param comment comment
     */
    void addComment(Comment comment);
}
