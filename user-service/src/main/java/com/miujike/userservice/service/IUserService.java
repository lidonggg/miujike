package com.miujike.userservice.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.userservice.domain.User;

/**
 * @author Ls J
 * @date 2019/3/30 12:57 PM
 */
public interface IUserService extends IService<User> {

    /**
     * 新用户注册
     * @param userInfoJSON
     * @param tapp
     * @return
     */
    User addNewUser(JSONObject userInfoJSON, String tapp);
}
