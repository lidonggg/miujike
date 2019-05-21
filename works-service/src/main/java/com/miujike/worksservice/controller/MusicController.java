package com.miujike.worksservice.controller;

import com.miujike.common.dto.ResponseData;
import com.miujike.worksservice.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ls J
 * @date 2019/4/13 1:05 PM
 */
@RestController
@RequestMapping("api/v1/works/music")
public class MusicController {

    @Autowired
    private IMusicService musicService;

    @RequestMapping("list/{userId}")
    public ResponseData listVideo(@PathVariable long userId, Long lastId) {
        if(null == lastId){
            lastId = 0L;
        }
        return new ResponseData<>(musicService.getUserMusicList(userId, lastId));
    }

    @GetMapping("/new")
    public ResponseData listNewMusic(){
        return new ResponseData<>(musicService.getNewMusicList());
    }
}
