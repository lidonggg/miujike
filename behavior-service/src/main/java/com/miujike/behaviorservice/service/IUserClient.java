package com.miujike.behaviorservice.service;

import com.miujike.behaviorservice.fallback.UserClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ls J
 * @date 2019/5/29 12:17 AM
 * user fegin
 */
@FeignClient(name = "user-service",fallback = UserClientFallBack.class)
public interface IUserClient {

    /**
     * 消费鸡蛋
     *
     * @param userId userId
     * @param eggs   eggs
     * @return boolean
     */
    @GetMapping("api/v1/user/behavior/subtractEggNum")
    boolean subtractEggNum(@RequestParam(value = "userId")long userId, @RequestParam(value = "eggs")int eggs);

    /**
     * 增加鸡蛋
     *
     * @param userId userId
     * @param eggs   eggs
     * @return boolean
     */
    @GetMapping("api/v1/user/behavior/addEggNum")
    boolean addEggNum(@RequestParam(value = "userId")long userId, @RequestParam(value = "eggs")int eggs);
}
