package com.miujike.worksservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.worksservice.domain.Music;

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
}
