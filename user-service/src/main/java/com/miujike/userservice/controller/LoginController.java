package com.miujike.userservice.controller;

import com.miujike.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ls J
 * @date 2019/4/3 1:04 PM
 * 登陆
 */
@Controller
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private IUserService userService;


}
