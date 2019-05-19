package com.miujike.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/5/19 5:14 PM
 */
public class MapCreator {

    public static Map<String,Object> createParamMap(Long userId, int fetchNum, Long lastId){
        Map<String,Object> map = new HashMap<>(6);
        map.put("userId", userId);
        map.put("lastId", lastId);
        map.put("fetchNum", fetchNum);
        return map;
    }
}
