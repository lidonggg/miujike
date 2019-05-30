package com.miujike.worksservice.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/1/12 10:54 AM
 */
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    FAIL(-1, "操作失败");

    private int code;

    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Map<String, Object> makeMap(boolean success) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", success);
        map.put("code", success ? ResponseCode.SUCCESS.getCode() : ResponseCode.FAIL.getCode());
        map.put("msg", success ? ResponseCode.SUCCESS.getMsg() : ResponseCode.FAIL.getMsg());
        return map;
    }
}
