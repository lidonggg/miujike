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
 * @date 2019/4/13 11:59 AM
 */
@TableName("miu_comment")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    @TableId("comment_id")
    private long commentId;

    @TableField("from_user_id")
    private long fromUserId;

    /**
     * 1--music
     * 2--video
     * 3-comment
     */
    @TableField("target_type")
    private int targetType;

    @TableField("target_id")
    private long targetId;

    private String content;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private int thumbs;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getThumbs() {
        return thumbs;
    }

    public void setThumbs(int thumbs) {
        this.thumbs = thumbs;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", fromUserId=" + fromUserId +
                ", targetType=" + targetType +
                ", targetId=" + targetId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", thumbs=" + thumbs +
                '}';
    }
}
