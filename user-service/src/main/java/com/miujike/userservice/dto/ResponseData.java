package com.miujike.userservice.dto;

import com.miujike.userservice.constants.ResponseInfo;

/**
 * @author Ls J
 * @date 2019/4/3 1:07 PM
 * 接口回复
 */
public class ResponseData<T> {

    private int code;

    private String msg;

    private T data;

    public ResponseData() {
        this.code = ResponseInfo.ERROR.getCode();
        this.msg = ResponseInfo.ERROR.getMsg();
        this.data = null;
    }

    public ResponseData(T data) {
        this.code = ResponseInfo.SUCCESS.getCode();
        this.msg = ResponseInfo.SUCCESS.getMsg();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
