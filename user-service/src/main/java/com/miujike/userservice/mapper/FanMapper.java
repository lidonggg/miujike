package com.miujike.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miujike.userservice.domain.Fan;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/13 12:58 PM
 */
public interface FanMapper extends BaseMapper<Fan> {

    /**
     * 获取用户关注列表
     *
     * @param map userId,lastId
     * @return list
     */
    List<Map<String, Object>> getUserFollowList(Map<String, Object> map);

    /**
     * 获取用户粉丝列表
     *
     * @param map userId,lastId
     * @return list
     */
    List<Map<String, Object>> getUserFanList(Map<String, Object> map);

}
