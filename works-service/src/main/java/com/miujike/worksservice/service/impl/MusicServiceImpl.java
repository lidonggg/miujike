package com.miujike.worksservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.worksservice.domain.Music;
import com.miujike.worksservice.mapper.MusicMapper;
import com.miujike.worksservice.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/10 6:38 PM
 */
@Service("musicService")
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements IMusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Value("${fetchNum}")
    private int fetchNum;

    @Override
    public int addThumbCount(long musicId) {
        Music music = musicMapper.selectById(musicId);
        music.setThumbs(music.getThumbs() + 1);

        return musicMapper.updateById(music);
    }

    @Override
    public int addCommentCount(long musicId) {
        Music music = musicMapper.selectById(musicId);
        music.setComments(music.getComments() + 1);

        return musicMapper.updateById(music);
    }

    @Override
    public List<Map<String,Object>> getUserMusicList(long userId,  Long lastId) {

        Map<String, Object> map = new HashMap<>(4);
        map.put("userId", userId);
        map.put("fetchNum", fetchNum);
        map.put("lastId", lastId);
        List<Map<String, Object>> resList = musicMapper.getUserMusicList(map);
        addDurationShow(resList);
        return null;
    }

    static void addDurationShow(List<Map<String, Object>> resList) {
        for (Map<String, Object> one : resList) {
            addDurationShowOfOne(one);
        }
    }

    static void addDurationShowOfOne(Map<String, Object> one){
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

    @Override
    public List<Map<String,Object>> getNewMusicList() {
        return musicMapper.getNewMusicList();
    }
}
