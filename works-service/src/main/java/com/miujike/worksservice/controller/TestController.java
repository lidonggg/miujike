package com.miujike.worksservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ls J
 * @date 2019/4/6 1:26 AM
 */
@Controller
@RequestMapping("api/v1/test")
public class TestController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
