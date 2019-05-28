package com.miujike.worksservice.controller;

import com.miujike.worksservice.domain.Music;
import com.miujike.worksservice.domain.Video;
import com.miujike.worksservice.service.IMusicService;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ls J
 * @date 2019/5/28 9:36 PM
 * fegin调用对象
 */
@RestController
@RequestMapping("api/v1/works/behavior")
public class FeginController {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IMusicService musicService;

    /**
     * 添加评论数，做冗余
     *
     * @param targetId   targetId
     * @param targetType targetType
     * @return
     */
    @GetMapping("addCommentNum")
    public boolean addCommentNum(Long targetId, int targetType) {
        boolean success = false;
        if(targetType == 1){
            Music music = musicService.getById(targetId);
            music.setComments(music.getComments() + 1);
            success = musicService.updateById(music);
        }else if(targetType == 2){
            Video video = videoService.getById(targetId);
            video.setComments(video.getComments() + 1);
            success = videoService.updateById(video);
        }

        return success;
    }

    /**
     * 添加点赞数，做冗余
     *
     * @param targetId   targetId
     * @param targetType targetType
     * @return
     */
    @GetMapping("addThumbNum")
    public boolean addThumbNum(Long targetId, int targetType) {
        boolean success = false;
        if(targetType == 1){
            Music music = musicService.getById(targetId);
            music.setThumbs(music.getThumbs() + 1);
            success = musicService.updateById(music);
        }else if(targetType == 2){
            Video video = videoService.getById(targetId);
            video.setThumbs(video.getThumbs() + 1);
            success = videoService.updateById(video);
        }

        return success;
    }

    /**
     * 添加分享，做冗余
     *
     * @param targetId   targetId
     * @param targetType targetType
     * @return
     */
    @GetMapping("addShareNum")
    public boolean addShareNum(Long targetId, int targetType) {
        boolean success = false;
        if(targetType == 1){
            Music music = musicService.getById(targetId);
            music.setShares(music.getShares() + 1);
            success = musicService.updateById(music);
        }else if(targetType == 2){
            Video video = videoService.getById(targetId);
            video.setShares(video.getShares() + 1);
            success = videoService.updateById(video);
        }

        return success;
    }
}
