package com.miujike.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miujike.userservice.domain.User;
import com.miujike.userservice.dto.ResponseData;
import com.miujike.userservice.service.IUserService;
import com.miujike.userservice.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ls J
 * @date 2019/4/3 1:04 PM
 * 登陆
 */
@Controller
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/doLogin")
    public ResponseData login(String encryptedData, String iv, String code, String tapp) {
        System.out.println(tapp);
        JSONObject userInfoJSON = WxUtil.decodeUserInfo(encryptedData, iv, code, tapp);
        System.out.println(userInfoJSON);
        if (null != userInfoJSON) {
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.lambda().eq(User::getMyOpenId,userInfoJSON.getString("openId"));
            User user = userService.getOne(userWrapper);
            if(null == user){
                user = userService.addNewUser(userInfoJSON,tapp);
            }
            return new ResponseData<>(user);
        }
        return null;
    }

}
