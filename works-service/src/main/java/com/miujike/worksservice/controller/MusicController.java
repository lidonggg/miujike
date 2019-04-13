package com.miujike.worksservice.controller;

import com.miujike.worksservice.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ls J
 * @date 2019/4/13 1:05 PM
 */
@Controller
@RequestMapping("api/v1/works/music")
public class MusicController {
    @Autowired
    private IMusicService musicService;
}
