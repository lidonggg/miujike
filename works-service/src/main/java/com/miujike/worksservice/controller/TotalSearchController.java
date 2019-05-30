package com.miujike.worksservice.controller;

import com.miujike.common.dto.ResponseData;
import com.miujike.worksservice.service.IMusicService;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/5/30 5:59 PM
 */
@RestController
@RequestMapping("/api/v1/works/both")
public class TotalSearchController {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IMusicService musicService;

    /**
     * 搜索
     * @param keyword
     * @return
     */
    @PostMapping("searchByKeyword")
    public ResponseData searchByKeyword(String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("videoList", videoService.searchByKeyword(keyword, 0));
        map.put("musicList", musicService.searchByKeyword(keyword, 0));
        return new ResponseData<>(map);
    }
}
