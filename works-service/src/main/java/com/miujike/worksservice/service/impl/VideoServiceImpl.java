package com.miujike.worksservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.worksservice.domain.Video;
import com.miujike.worksservice.mapper.VideoMapper;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/4/10 6:39 PM
 */
@Service("videoService")
public class VideoServiceImpl extends ServiceImpl<VideoMapper,Video> implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public int addThumbCount(long videoId) {
        Video video = videoMapper.selectById(videoId);
        video.setThumbs(video.getThumbs() + 1);

        return videoMapper.updateById(video);
    }

    @Override
    public int addCommentCount(long videoId) {
        Video video = videoMapper.selectById(videoId);
        video.setComments(video.getComments() + 1);

        return videoMapper.updateById(video);
    }
}
