package com.miujike.userservice.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/10 6:21 PM
 * 签到日志
 */
@TableName("miu_sign_log")
public class SignLog extends Model<SignLog> {

    private static final long serialVersionUID = 1L;

    @TableId("sign_id")
    private long signId;

    @TableField("user_id")
    private long userId;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 本次签到获得的鸡蛋数
     */
    @TableField("eggs_get")
    private int eggsGet;

    public long getSignId() {
        return signId;
    }

    public void setSignId(long signId) {
        this.signId = signId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getEggsGet() {
        return eggsGet;
    }

    public void setEggsGet(int eggsGet) {
        this.eggsGet = eggsGet;
    }

    @Override
    public String toString() {
        return "SignLog{" +
                "signId=" + signId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", eggsGet=" + eggsGet +
                '}';
    }
}
