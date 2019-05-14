package com.miujike.worksservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.worksservice.domain.Video;

/**
 * @author Ls J
 * @date 2019/4/10 6:38 PM
 */
public interface IVideoService extends IService<Video> {

    /**
     * 添加点赞人数
     * @param videoId videoId
     * @return
     */
    int addThumbCount(long videoId);

    /**
     * 添加评论
     * @param videoId videoId
     * @return
     */
    int addCommentCount(long videoId);
}
