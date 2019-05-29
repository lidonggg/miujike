package com.miujike.worksservice.controller;

import com.miujike.common.dto.ResponseData;
import com.miujike.worksservice.domain.Music;
import com.miujike.worksservice.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ls J
 * @date 2019/4/13 1:05 PM
 */
@RestController
@RequestMapping("api/v1/works/music")
public class MusicController {

    @Autowired
    private IMusicService musicService;

    @PostMapping("upload")
    public ResponseData upload(Music music) {
        Music musicAdded = musicService.saveNewMusic(music);
        return 0L != musicAdded.getMusicId() ? new ResponseData<>(musicAdded) : new ResponseData();
    }

    /**
     * 拉取音乐
     *
     * @param userId
     * @param lastId
     * @return
     */
    @RequestMapping("/list/{userId}")
    public ResponseData listMusic(@PathVariable long userId, Long lastId) {
        if (null == lastId) {
            lastId = 0L;
        }
        return new ResponseData<>(musicService.getUserMusicList(userId, lastId));
    }

    /**
     * 拉取喜欢的音乐
     *
     * @param userId
     * @param lastId
     * @return
     */
    @RequestMapping("/listLike/{userId}")
    public ResponseData listLike(@PathVariable long userId, Long lastId) {
        if (null == lastId) {
            lastId = 0L;
        }
        return new ResponseData<>(musicService.getUserMusicListLike(userId, lastId));
    }

    @GetMapping("/new")
    public ResponseData listNewMusic() {
        return new ResponseData<>(musicService.getNewMusicList());
    }

    /**
     * 按照受欢迎程度进行分页
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseData listPopularVideo(int curPage) {

        return new ResponseData<>();
    }
}
