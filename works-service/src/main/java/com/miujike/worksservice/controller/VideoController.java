package com.miujike.worksservice.controller;

import com.miujike.common.constants.BaseController;
import com.miujike.common.dto.ResponseData;
import com.miujike.worksservice.domain.Video;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
     * 拉取喜欢的视频
     *
     * @param userId
     * @param lastId
     * @return
     */
    @RequestMapping("listLike/{userId}")
    public ResponseData listLike(@PathVariable Long userId, Long lastId) {
        if (null == userId) {
            userId = 0L;
        }
        if (null == lastId) {
            lastId = 0L;
        }
        return new ResponseData<>(videoService.getUserVideoListLike(userId, lastId));
    }

    /**
     * 拉取最新的n个官方视频
     *
     * @param num
     * @return
     */
    @GetMapping("/newOfficial")
    public ResponseData listNewOfficialVideo(int num) {
        return new ResponseData<>(videoService.getOfficialNewVideoList(num));
    }

    /**
     * 拉取最新的n个视频
     *
     * @param num
     * @return
     */
    @GetMapping("/new")
    public ResponseData listNewVideo(int num) {
        return new ResponseData<>(videoService.getNewVideoList(num));
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

    /**
     * 增加播放次数
     *
     * @param videoId
     * @return
     */
    @GetMapping("addPlayTimes")
    public ResponseData addPlayTimes(long videoId) {
        Video video = videoService.getById(videoId);
        video.setPlayTimes(video.getPlayTimes() + 1);
        boolean success = videoService.updateById(video);
        return success ? new ResponseData<>(1) : new ResponseData();
    }

    /**
     * 关键词搜索
     *
     * @param keyword
     * @param lastId  暂时没有用到
     * @return
     */
    @PostMapping("searchByKeyword")
    public ResponseData searchByKeyword(String keyword, long lastId) {

        return new ResponseData<>(videoService.searchByKeyword(keyword, lastId));
    }
}
