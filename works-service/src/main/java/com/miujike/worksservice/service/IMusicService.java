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
     * 点赞，添加点赞人数
     * @param musicId musicId
     * @return
     */
    int addThumbCount(long musicId);

    /**
     * 添加评论
     * @param musicId musicId
     * @return
     */
    int addCommentCount(long musicId);

    /**
     * 拉取用户music列表（分页）
     * @param userId userId
     * @param lastId lastId
     * @return list
     */
    List<Map<String,Object>> getUserMusicList(long userId,  Long lastId);

    /**
     * 获取最新的五首音乐
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getNewMusicList();
}
