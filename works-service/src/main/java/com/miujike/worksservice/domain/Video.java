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
@TableName("miu_video")
public class Video extends Model<Video> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "video_id", type = IdType.AUTO)
    private long videoId;

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

    @TableField("video_url")
    private String videoUrl;

    private String cover;

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
     * 目前没有用到，用点赞代替鸡蛋数
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

    /**
     * 播放次数
     */
    @TableField("play_times")
    private int playTimes;

    private int shares;

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public int getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(int playTimes) {
        this.playTimes = playTimes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", userId=" + userId +
                ", singer='" + singer + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", cover='" + cover + '\'' +
                ", duration=" + duration +
                ", status=" + status +
                ", createTime=" + createTime +
                ", releaseTime=" + releaseTime +
                ", eggs=" + eggs +
                ", thumbs=" + thumbs +
                ", comments=" + comments +
                ", originalSinger='" + originalSinger + '\'' +
                ", playTimes=" + playTimes +
                ", shares=" + shares +
                '}';
    }
}
