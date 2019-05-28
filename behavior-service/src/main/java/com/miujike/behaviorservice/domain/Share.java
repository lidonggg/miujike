package com.miujike.behaviorservice.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/5/28 11:43 PM
 */
@TableName("miu_share")
public class Share extends Model<Share> {

    private static final long serialVersionUID = 1L;

    @TableId("share_id")
    private long shareId;

    @TableField("from_user_id")
    private Long fromUserId;

    @TableField("target_id")
    private Long targetId;

    @TableField("target_type")
    private int targetType;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private int eggs;

    public long getShareId() {
        return shareId;
    }

    public void setShareId(long shareId) {
        this.shareId = shareId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getEggs() {
        return eggs;
    }

    public void setEggs(int eggs) {
        this.eggs = eggs;
    }

    @Override
    public String toString() {
        return "Share{" +
                "shareId=" + shareId +
                ", fromUserId=" + fromUserId +
                ", targetId=" + targetId +
                ", targetType=" + targetType +
                ", createTime=" + createTime +
                ", eggs=" + eggs +
                '}';
    }
}
