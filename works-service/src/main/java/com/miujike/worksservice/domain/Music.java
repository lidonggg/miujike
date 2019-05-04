package com.miujike.worksservice.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/6 1:31 AM
 */
@TableName("miu_music")
public class Music extends Model<Music> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "music_id", type = IdType.AUTO)
    private long musicId;

    @TableField("user_id")
    private long userId;

    /**
     * 演唱者
     */
    private String singer;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String detail;

    @TableField("music_url")
    private String musicUrl;

    private int duration;

    private short status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 发布时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    /**
     * 获得鸡蛋数，冗余
     */
    private int eggs;

    /**
     * 获得点赞数，冗余
     */
    private int thumbs;

    /**
     * 获得评论数，冗余
     */
    private int comments;

    /**
     * 原唱
     */
    private String originalSinger;

    public long getMusicId() {
        return musicId;
    }

    public void setMusicId(long musicId) {
        this.musicId = musicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getEggs() {
        return eggs;
    }

    public void setEggs(int eggs) {
        this.eggs = eggs;
    }

    public int getThumbs() {
        return thumbs;
    }

    public void setThumbs(int thumbs) {
        this.thumbs = thumbs;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getOriginalSinger() {
        return originalSinger;
    }

    public void setOriginalSinger(String originalSinger) {
        this.originalSinger = originalSinger;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", userId=" + userId +
                ", singer='" + singer + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", duration=" + duration +
                ", status=" + status +
                ", createTime=" + createTime +
                ", releaseTime=" + releaseTime +
                ", eggs=" + eggs +
                ", thumbs=" + thumbs +
                ", comments=" + comments +
                ", originalSinger='" + originalSinger + '\'' +
                '}';
    }
}
