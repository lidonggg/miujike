package com.miujike.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.userservice.domain.Fan;
import com.miujike.userservice.mapper.FanMapper;
import com.miujike.userservice.service.IFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/13 12:59 PM
 */
@Service("fanService")
public class FanServiceImpl extends ServiceImpl<FanMapper, Fan> implements IFanService {

    @Autowired
    private FanMapper fanMapper;

    @Override
    public List<Map<String, Object>> getUserFollowList(Long userId, int fetchNum, Long lastId) {
        Map<String, Object> map = new HashMap<>(6);
        map.put("userId", userId);
        map.put("lastId", lastId);
        map.put("fetchNum", fetchNum);
        return fanMapper.getUserFanList(map);
    }

    @Override
    public List<Map<String, Object>> getUserFanList(Long userId, int fetchNum, Long lastId) {
        Map<String, Object> map = new HashMap<>(6);
        map.put("userId", userId);
        map.put("lastId", lastId);
        map.put("fetchNum", fetchNum);
        return fanMapper.getUserFollowList(map);
    }
}
