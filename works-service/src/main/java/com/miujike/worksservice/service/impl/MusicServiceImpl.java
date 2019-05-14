package com.miujike.worksservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.worksservice.domain.Music;
import com.miujike.worksservice.mapper.MusicMapper;
import com.miujike.worksservice.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/4/10 6:38 PM
 */
@Service("musicService")
public class MusicServiceImpl extends ServiceImpl<MusicMapper,Music> implements IMusicService {

    @Autowired
    private MusicMapper musicMapper;

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
}
