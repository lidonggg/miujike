package com.miujike.worksservice.vo;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Ls J
 * @date 2018/10/29 9:37 AM
 * 自定义返回值
 */
public class ResponseData {

    private static final long serialVersionUID = 1L;

    private String code = "200";

    private String msg = "成功！";

    private Date date = new Date();

    private Object data;

    public ResponseData() {
    }

    public ResponseData(Object data) {
        this.data = data;
    }

    public ResponseData(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        this.data = map;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ResponeData [code=" + code + ", msg=" + msg + ", date=" + date + ", data=" + data + "]";
    }

}
