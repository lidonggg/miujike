package com.miujike.worksservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.worksservice.domain.Video;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/10 6:38 PM
 */
public interface IVideoService extends IService<Video> {

    /**
     * 添加点赞人数
     *
     * @param videoId videoId
     * @return
     */
    int addThumbCount(long videoId);

    /**
     * 添加评论
     *
     * @param videoId videoId
     * @return
     */
    int addCommentCount(long videoId);

    /**
     * 拉取用户video列表（分页）
     *
     * @param userId   userId
     * @param fetchNum 开始位置
     * @param lastId   结束位置
     * @return list
     */
    List<Map<String, Object>> getUserVideoList(long userId, int fetchNum, Long lastId);
}
