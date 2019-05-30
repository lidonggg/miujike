package com.miujike.worksservice.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ls J
 * @date 2019/5/30 7:19 PM
 */
@Controller
@RequestMapping("api/v1/works/page")
public class PageController {

    @RequestMapping("admin")
    public String admin(){
        return "/index";
    }

    @RequestMapping("video")
    public String video(){
        return "works/videoList";
    }
}
