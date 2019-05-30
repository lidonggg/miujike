package com.miujike.worksservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.common.dto.MapCreator;
import com.miujike.common.util.QiNiuUtil;
import com.miujike.worksservice.domain.Video;
import com.miujike.worksservice.mapper.VideoMapper;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/10 6:39 PM
 */
@Service("videoService")
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Value("${fetchNum}")
    private int fetchNum;

    @Override
    public Video saveNewVideo(Video video) {
        if (null == video.getCover()) {
            video.setCover(video.getVideoUrl() + QiNiuUtil.coverParam);
        }
        video.setCreateTime(new Date());
        video.setReleaseTime(new Date());
        System.out.print(video.toString());
        save(video);
        int duration = video.getDuration();
        int hour = (duration / 1000) / 3600;
        int minute = (duration / 1000 - hour * 3660) / 60;
        int second = duration / 1000 - hour * 3660 - minute * 60;
        String durationShow = (hour == 0 ? "" : (hour < 10 ? "0" + hour + ":" : "" + hour + ":"))
                + (minute < 10 ? "0" + minute : "" + minute) + ":"
                + (second < 10 ? "0" + second : "" + second);
        video.setDurationShow(durationShow);
        return video;
    }

    @Override
    public int addThumbCount(long videoId) {
        Video video = videoMapper.selectById(videoId);
        video.setThumbs(video.getThumbs() + 1);

        return videoMapper.updateById(video);
    }

    @Override
    public int addCommentCount(long videoId) {
        Video video = videoMapper.selectById(videoId);
        video.setComments(video.getComments() + 1);

        return videoMapper.updateById(video);
    }

    @Override
    public Map<String, Object> getVideoInfo(long videoId) {
        Map<String, Object> map = videoMapper.getVideoInfo(videoId);
        MusicServiceImpl.addDurationShowOfOne(map);
        return map;
    }

    @Override
    public List<Map<String, Object>> getUserVideoList(long userId, Long lastId) {
        Map<String, Object> map = MapCreator.createParamMap(userId, fetchNum, lastId);
        ;
        List<Map<String, Object>> resList = videoMapper.getUserVideoList(map);
        MusicServiceImpl.addDurationShow(resList);
        return resList;
    }

    @Override
    public List<Map<String, Object>> getUserVideoListLike(long userId, Long lastId) {
        Map<String, Object> map = MapCreator.createParamMap(userId, fetchNum, lastId);
        List<Map<String, Object>> resList = videoMapper.getUserVideoListLike(map);
        MusicServiceImpl.addDurationShow(resList);
        return resList;
    }

    @Override
    public List<Map<String, Object>> getNewVideoList(int n) {
        return videoMapper.getNewVideoList(n);
    }

    @Override
    public List<Map<String, Object>> getOfficialNewVideoList(int n) {
        List<Map<String, Object>> resList = videoMapper.getOfficialNewVideoList(n);
        MusicServiceImpl.addDurationShow(resList);
        return resList;
    }

    @Override
    public List<Map<String, Object>> getRecommendVideoList(long curVideoId, int n) {
        Map<String, Object> map = new HashMap<>();
        map.put("curVideoId", curVideoId);
        map.put("n", n);
        List<Map<String, Object>> resList = videoMapper.getRecommendVideoList(map);
        MusicServiceImpl.addDurationShow(resList);
        return resList;
    }

    @Override
    public List<Map<String, Object>> searchByKeyword(String keyword, long lastId) {
        String searchMode = "%" + keyword + "%";
        Map<String, Object> map = new HashMap<>();
        map.put("fetchNum", fetchNum);
        map.put("searchMode", searchMode);
        map.put("lastId", lastId);
        List<Map<String, Object>> resList = videoMapper.listByKeyword(map);
        MusicServiceImpl.addDurationShow(resList);
        return resList;
    }
}
