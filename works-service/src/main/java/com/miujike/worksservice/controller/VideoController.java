package com.miujike.worksservice.controller;

import com.miujike.common.constants.BaseController;
import com.miujike.common.dto.ResponseData;
import com.miujike.worksservice.domain.Video;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 1:06 PM
 */
@RestController
@RequestMapping("api/v1/works/video")
public class VideoController extends BaseController {

    @Value("${fetchNum}")
    private int fetchNum;

    @Autowired
    private IVideoService videoService;

    @PostMapping("upload")
    public ResponseData upload(Video video) {
        Video videoAdded = videoService.saveNewVideo(video);
        return 0L != videoAdded.getVideoId() ? new ResponseData<>(videoAdded) : new ResponseData();
    }

    /**
     * 获取某一条视频的信息
     *
     * @return
     */
    @RequestMapping("info/{videoId}")
    public ResponseData getVideoInfo(@PathVariable long videoId) {
        return new ResponseData<>(videoService.getVideoInfo(videoId));
    }

    /**
     * 拉取视频
     *
     * @param userId
     * @param lastId
     * @return
     */
    @RequestMapping("list/{userId}")
    public ResponseData listVideo(@PathVariable Long userId, Long lastId) {
        if (null == userId) {
            userId = 0L;
        }
        if (null == lastId) {
            lastId = 0L;
        }
        return new ResponseData<>(videoService.getUserVideoList(userId, lastId));
    }

    /**
     * 拉取最新的五个视频
     *
     * @return
     */
    @GetMapping("/new")
    public ResponseData listNewVideo() {
        return new ResponseData<>(videoService.getNewVideoList());
    }

    /**
     * 随机推荐
     *
     * @param n
     * @return
     */
    @GetMapping("recommend/{curVideoId}")
    public ResponseData listRecommend(@PathVariable Long curVideoId, int n) {
        if (null == curVideoId) {
            curVideoId = 0L;
        }
        return new ResponseData<>(videoService.getRecommendVideoList(curVideoId, n));
    }
}
