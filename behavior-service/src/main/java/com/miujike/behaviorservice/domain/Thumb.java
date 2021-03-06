package com.miujike.behaviorservice.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 12:00 PM
 */
@TableName("miu_thumb")
public class Thumb extends Model<Thumb> {

    private static final long serialVersionUID = 1L;

    @TableId("thumb_id")
    private long thumbId;

    @TableField("from_user_id")
    private Long fromUserId;

    /**
     * 1--music
     * 2--video
     * 3-comment
     */
    @TableField("target_type")
    private int targetType;

    @TableField("target_id")
    private Long targetId;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private int eggs;

    public long getThumbId() {
        return thumbId;
    }

    public void setThumbId(long thumbId) {
        this.thumbId = thumbId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
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
        return "Thumb{" +
                "thumbId=" + thumbId +
                ", fromUserId=" + fromUserId +
                ", targetType=" + targetType +
                ", targetId=" + targetId +
                ", createTime=" + createTime +
                ", eggs=" + eggs +
                '}';
    }
}
