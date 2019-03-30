package com.miujike.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.userservice.domain.User;
import com.miujike.userservice.mapper.UserMapper;
import com.miujike.userservice.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/3/30 12:57 PM
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
}
