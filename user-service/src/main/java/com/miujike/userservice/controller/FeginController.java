package com.miujike.userservice.controller;

import com.miujike.userservice.domain.User;
import com.miujike.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ls J
 * @date 2019/5/29 12:06 AM
 * fegin调用控制器
 */
@RestController
@RequestMapping("api/v1/user/behavior")
public class FeginController {

    @Autowired
    private IUserService userService;

    /**
     * 消费鸡蛋
     * @param userId
     * @param eggs
     * @return
     */
    @GetMapping("subtractEggNum")
    public boolean subtractEggNum(long userId, int eggs) {
        User user = userService.getById(userId);
        int eggHas = user.getEggs();
        if(eggHas == 0){
            return false;
        } else if(eggHas < eggs){
            return false;
        }
        user.setEggs(eggHas - eggs);
        return userService.updateById(user);
    }

    /**
     * 增加鸡蛋
     * @param userId
     * @param eggs
     * @return
     */
    @GetMapping("addEggNum")
    public boolean addEggNum(long userId, int eggs) {
        User user = userService.getById(userId);

        user.setEggs(user.getEggs() + eggs);
        return userService.updateById(user);
    }
}
