package com.miujike.userservice.constants;

/**
 * @author Ls J
 * @date 2019/4/3 1:10 PM
 */
public enum ResponseInfo {

    /**
     * 成功
     */
    SUCCESS(200,"成功"),

    /**
     * 失败
     */
    ERROR(-1,"失败");

    private int code;

    private String msg;

    ResponseInfo(int code, String msg) {
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
}
