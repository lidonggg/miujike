package com.miujike.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.userservice.domain.Fan;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/13 12:59 PM
 */
public interface IFanService extends IService<Fan> {

    /**
     * 获取用户关注
     *
     * @param userId userId
     * @param lastId lastId
     * @return list
     */
    List<Map<String, Object>> getUserFollowList(Long userId, Long lastId);

    /**
     * 获取用户粉丝
     *
     * @param userId userId
     * @param lastId lastId
     * @return list
     */
    List<Map<String, Object>> getUserFanList(Long userId, Long lastId);
}
