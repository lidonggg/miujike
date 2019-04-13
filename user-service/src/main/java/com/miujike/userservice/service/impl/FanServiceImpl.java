package com.miujike.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.userservice.domain.Fan;
import com.miujike.userservice.mapper.FanMapper;
import com.miujike.userservice.service.IFanService;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/4/13 12:59 PM
 */
@Service("fanService")
public class FanServiceImpl extends ServiceImpl<FanMapper,Fan> implements IFanService {
}
