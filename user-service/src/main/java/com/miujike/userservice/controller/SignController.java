package com.miujike.userservice.controller;

import com.miujike.common.dto.ResponseData;
import com.miujike.common.util.DateUtil;
import com.miujike.userservice.domain.SignLog;
import com.miujike.userservice.domain.User;
import com.miujike.userservice.service.ISignLogService;
import com.miujike.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 1:04 PM
 */
@RestController
@RequestMapping("api/v1/user/sign")
public class SignController {

    @Autowired
    private ISignLogService signLogService;

    @Autowired
    private IUserService userService;

    @GetMapping("{userId}")
    public ResponseData doSign(@PathVariable Long userId) {
        User user = userService.getById(userId);
        if (null == user.getLastSignTime() || !DateUtil.isNowDate(user.getLastSignTime())) {
            SignLog signLog = new SignLog();
            Date now = new Date();
            signLog.setCreateTime(now);
            signLog.setUserId(userId);
            int eggs = (int) (1 + Math.random() * (10 - 1 + 1));
            signLog.setEggsGet(eggs);
            signLogService.save(signLog);

            user.setLastSignTime(now);
            user.setEggs(user.getEggs() + eggs);
            userService.updateById(user);
            return new ResponseData<>(eggs);
        }
        return new ResponseData();
    }
}
