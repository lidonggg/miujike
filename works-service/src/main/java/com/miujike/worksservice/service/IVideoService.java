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
     * 添加新视频
     *
     * @param video
     * @return
     */
    Video saveNewVideo(Video video);

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
     * 获取某条视频的信息
     *
     * @param videoId videoId
     * @return map
     */
    Map<String, Object> getVideoInfo(long videoId);

    /**
     * 拉取用户video列表（分页）
     *
     * @param userId userId
     * @param lastId 结束位置
     * @return list
     */
    List<Map<String, Object>> getUserVideoList(long userId, Long lastId);

    /**
     * 拉取用户喜欢的video列表（分页）
     *
     * @param userId userId
     * @param lastId 结束位置
     * @return list
     */
    List<Map<String, Object>> getUserVideoListLike(long userId, Long lastId);

    /**
     * 获取最新的五个视频
     *
     * @return List<Map   <   String   ,   Object>>
     */
    List<Map<String, Object>> getNewVideoList();

    /**
     * 随机n条数据，作为推荐
     *
     * @param curVideoId
     * @param n
     * @return list
     */
    List<Map<String, Object>> getRecommendVideoList(long curVideoId, int n);

    /**
     * 关键词搜索
     *
     * @param keyword
     * @param lastId
     * @return
     */
    List<Map<String, Object>> searchByKeyword(String keyword,long lastId);
}
