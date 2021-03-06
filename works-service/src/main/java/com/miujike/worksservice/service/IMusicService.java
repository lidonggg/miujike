package com.miujike.worksservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.worksservice.domain.Music;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/10 6:38 PM
 */
public interface IMusicService extends IService<Music> {

    /**
     * 添加新视频
     *
     * @param music
     * @return
     */
    Music saveNewMusic(Music music);

    /**
     * 点赞，添加点赞人数
     *
     * @param musicId musicId
     * @return
     */
    int addThumbCount(long musicId);

    /**
     * 添加评论
     *
     * @param musicId musicId
     * @return
     */
    int addCommentCount(long musicId);

    /**
     * 拉取用户music列表（分页）
     *
     * @param userId userId
     * @param lastId lastId
     * @return list
     */
    List<Map<String, Object>> getUserMusicList(long userId, Long lastId);

    /**
     * 获取最流行的音乐列表
     *
     * @return list
     */
    List<Map<String, Object>> getPopularMusicList();

    /**
     * 拉取用户喜欢的music列表（分页）
     *
     * @param userId userId
     * @param lastId lastId
     * @return list
     */
    List<Map<String, Object>> getUserMusicListLike(long userId, Long lastId);

    /**
     * 获取最新的五首音乐
     *
     * @param n
     * @return list
     */
    List<Map<String, Object>> getNewMusicList(int n);

    /**
     * 关键词搜索
     *
     * @param keyword
     * @param lastId
     * @return
     */
    List<Map<String, Object>> searchByKeyword(String keyword, long lastId);
}
