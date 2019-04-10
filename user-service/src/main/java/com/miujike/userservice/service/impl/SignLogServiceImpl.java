package com.miujike.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.userservice.domain.SignLog;
import com.miujike.userservice.mapper.SignLogMapper;
import com.miujike.userservice.service.ISignLogService;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/4/10 6:31 PM
 */
@Service("signLogService")
public class SignLogServiceImpl extends ServiceImpl<SignLogMapper,SignLog> implements ISignLogService {
}
