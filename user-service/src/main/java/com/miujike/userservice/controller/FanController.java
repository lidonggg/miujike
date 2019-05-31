package com.miujike.userservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miujike.common.constants.BaseController;
import com.miujike.common.dto.ResponseData;
import com.miujike.userservice.domain.Fan;
import com.miujike.userservice.service.IFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 1:03 PM
 */
@RestController
@RequestMapping("api/v1/user")
public class FanController extends BaseController {

    @Value("${fetchNum}")
    private int fetchNum;

    @Autowired
    private IFanService fanService;

    /**
     *
     * @param fromUserId
     * @param toUserId
     * @return
     */
    @GetMapping("follow/getIfFollowed")
    public ResponseData getIfFollowed(long fromUserId, long toUserId) {
        QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
        fanWrapper.eq("from_user_id", fromUserId)
                .eq("to_user_id", toUserId);
        Fan fan = fanService.getOne(fanWrapper);
        return null == fan ? new ResponseData<>() : new ResponseData<>(1);
    }

    /**
     * 获取粉丝数
     *
     * @param userId userId
     * @return ResponseData
     */
    @GetMapping("fan/number/{userId}")
    public ResponseData countFans(@PathVariable long userId) {
        QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
        fanWrapper.eq("to_user_id", userId);

        return new ResponseData<>(fanService.count(fanWrapper));
    }

    /**
     * 获取关注数
     *
     * @param userId userId
     * @return ResponseData
     */
    @GetMapping("follow/number/{userId}")
    public ResponseData countFollows(@PathVariable long userId) {
        QueryWrapper<Fan> followWrapper = new QueryWrapper<>();
        followWrapper.eq("from_user_id", userId);

        return new ResponseData<>(fanService.count(followWrapper));
    }

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
        return new ResponseData<>(fanService.getUserFollowList(userId, lastId));
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
        return new ResponseData<>(fanService.getUserFanList(userId, lastId));
    }

    /**
     * 取消关注
     *
     * @param fromUserId fromUserId
     * @param toUserId   toUserId
     * @return ResponseData
     */
    @GetMapping("fan/unfollow")
    public ResponseData unFollow(Long fromUserId, Long toUserId) {
        QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
        fanWrapper.eq("from_user_id", fromUserId)
                .eq("to_user_id", toUserId);
        boolean success = fanService.remove(fanWrapper);

        return success ? new ResponseData<>(true) : new ResponseData();
    }

    /**
     * 关注
     *
     * @param fromUserId fromUserId
     * @param toUserId   toUserId
     * @return ResponseData
     */
    @GetMapping("fan/follow")
    public ResponseData follow(Long fromUserId, Long toUserId) {
        QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
        fanWrapper.eq("from_user_id", fromUserId)
                .eq("to_user_id", toUserId);
        Fan newFan = fanService.getOne(fanWrapper);
        if (null == newFan) {
            newFan = new Fan();
            newFan.setFromUserId(fromUserId);
            newFan.setToUserId(toUserId);
            newFan.setCreateTime(new Date());
            boolean success = fanService.save(newFan);

            return new ResponseData<>(success);
        }


        return new ResponseData();
    }
}
