package com.miujike.userservice.controller;

import com.miujike.userservice.domain.User;
import com.miujike.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ls J
 * @date 2019/3/30 12:53 PM
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    /**
     * 负载均衡的时候，确定调用的是哪个端口的服务
     */
    @Value("${server.port}")
    private String port;

    @Autowired
    private IUserService userService;

    @RequestMapping("insert")
    @ResponseBody
    public String insert(){
        User user = new User();
        user.setAvatarUrl("1111");
        user.setCity("111");
        userService.save(user);

        return "success";
    }

    @RequestMapping("sayhello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }
}
