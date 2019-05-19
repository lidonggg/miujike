package com.miujike.userservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.userservice.domain.User;
import com.miujike.userservice.mapper.UserMapper;
import com.miujike.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/3/30 12:57 PM
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User addNewUser(JSONObject userInfoJSON, String tapp) {
        User user = new User();
        user.setCity(userInfoJSON.containsKey("city")?userInfoJSON.getString("city"):"");
        user.setCountry(userInfoJSON.containsKey("country")?userInfoJSON.getString("country"):"");
        user.setProvince(userInfoJSON.containsKey("province")?userInfoJSON.getString("province"):"");
        user.setGender(userInfoJSON.containsKey("gender")?userInfoJSON.getInteger("gender"):0);
        user.setAvatarUrl(userInfoJSON.containsKey("avatarUrl")?userInfoJSON.getString("avatarUrl"):"");
        user.setCreateTime(new Date());
        user.setLastLoginTime(new Date());
        user.setNickname(userInfoJSON.containsKey("nickName")?userInfoJSON.getString("nickName"):"");
        user.setOpenId(userInfoJSON.getString("openId"));
        userMapper.insert(user);
        return user;
    }
}
