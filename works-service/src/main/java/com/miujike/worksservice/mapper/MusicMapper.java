package com.miujike.worksservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miujike.worksservice.domain.Music;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/10 6:37 PM
 */
public interface MusicMapper extends BaseMapper<Music> {

    /**
     * 拉取用户music列表（分页）
     * @param map userId, start, end
     * @return list
     */
    List<Map<String,Object>> getUserMusicList(Map<String,Object> map);

    /**
     * 获取最新的五首音乐
     * @return List<Music>
     */
    List<Music> getNewMusicList();
}
