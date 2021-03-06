package com.miujike.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.common.dto.MapCreator;
import com.miujike.userservice.domain.Fan;
import com.miujike.userservice.mapper.FanMapper;
import com.miujike.userservice.service.IFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Value("${fetchNum}")
    private int fetchNum;

    @Override
    public List<Map<String, Object>> getUserFollowList(Long userId, Long lastId) {
        return fanMapper.getUserFollowList(MapCreator.createParamMap(userId,fetchNum,lastId));
    }

    @Override
    public List<Map<String, Object>> getUserFanList(Long userId, Long lastId) {
        return fanMapper.getUserFanList(MapCreator.createParamMap(userId,fetchNum,lastId));
    }
}
