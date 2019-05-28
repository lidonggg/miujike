package com.miujike.behaviorservice.fallback;

import com.miujike.behaviorservice.service.IUserClient;

/**
 * @author Ls J
 * @date 2019/5/29 12:18 AM
 * 针对用户降级
 */
public class UserClientFallBack implements IUserClient {
    @Override
    public boolean subtractEggNum(long userId, int eggs) {
        return false;
    }

    @Override
    public boolean addEggNum(long userId, int eggs) {
        return false;
    }
}
