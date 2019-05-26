package com.miujike.worksservice.controller;

import com.miujike.common.constants.BaseController;
import com.miujike.common.dto.ResponseData;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("list/{userId}")
    public ResponseData listVideo(@PathVariable long userId, Long lastId) {
        if(null == lastId){
            lastId = 0L;
        }
        return new ResponseData<>(videoService.getUserVideoList(userId, lastId));
    }

    /**
     * 拉取最新的五个视频
     * @return
     */
    @GetMapping("/new")
    public ResponseData listNewVideo(){
        return new ResponseData<>(videoService.getNewVideoList());
    }
}
