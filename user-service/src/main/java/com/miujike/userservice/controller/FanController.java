package com.miujike.userservice.controller;

import com.miujike.common.dto.ResponseData;
import com.miujike.userservice.service.IFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ls J
 * @date 2019/4/13 1:03 PM
 */
@RestController
@RequestMapping("api/v1/user")
public class FanController {

    @Value("${fetchNum}")
    private int fetchNum;

    @Autowired
    private IFanService fanService;

    /**
     * 获取关注列表
     *
     * @param userId userId
     * @param lastId lastId
     * @return ResponseData
     */
    @GetMapping("/follow/list/{userId}")
    public ResponseData listFollow(@PathVariable Long userId, Long lastId) {
        if (null == lastId) {
            lastId = 0L;
        }
        return new ResponseData<>(fanService.getUserFollowList(userId, fetchNum, lastId));
    }

    /**
     * 获取粉丝列表
     *
     * @param userId userId
     * @param lastId lastId
     * @return ResponseData
     */
    @GetMapping("/fan/list/{userId}")
    public ResponseData listFan(@PathVariable Long userId, Long lastId) {
        if (null == lastId) {
            lastId = 0L;
        }
        return new ResponseData<>(fanService.getUserFanList(userId, fetchNum, lastId));
    }
}
