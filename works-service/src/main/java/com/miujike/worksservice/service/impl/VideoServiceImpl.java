package com.miujike.worksservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.worksservice.domain.Video;
import com.miujike.worksservice.mapper.VideoMapper;
import com.miujike.worksservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Map<String, Object>> getUserVideoList(long userId, int fetchNum, Long lastId) {
        Map<String, Object> map = new HashMap<>(6);
        map.put("userId", userId);
        map.put("fetchNum", fetchNum);
        map.put("lastId", lastId);
        List<Map<String, Object>> resList = videoMapper.getUserVideoList(map);
        for (Map<String, Object> one : resList) {
            if (one.containsKey("duration")) {
                int duration = (int) one.get("duration");
                int hour = duration / 3600;
                int minute = (duration - hour * 3660) / 60;
                int second = duration - hour * 3660 - minute * 60;
                String durationShow = (hour == 0 ? "" : (hour < 10 ? "0" + hour + ":" : "" + hour + ":"))
                        + (minute < 10 ? "0" + minute : "" + minute) + ":"
                        + (second < 10 ? "0" + second : "" + second);
                one.put("durationShow", durationShow);
            }
        }
        return resList;
    }
}
