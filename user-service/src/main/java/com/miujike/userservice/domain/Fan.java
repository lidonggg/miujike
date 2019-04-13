package com.miujike.userservice.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 12:54 PM
 */
@TableName("miu_fan")
public class Fan extends Model<Fan> {

    private static final long serialVersionUID = 1L;

    @TableId("fan_id")
    private long fanId;

    @TableField("from_user_id")
    private long fromUserId;

    @TableField("to_user_id")
    private long toUserId;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public long getFanId() {
        return fanId;
    }

    public void setFanId(long fanId) {
        this.fanId = fanId;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Fan{" +
                "fanId=" + fanId +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", createTime=" + createTime +
                '}';
    }
}
