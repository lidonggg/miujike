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
     *
     * @param map userId, start, end
     * @return list
     */
    List<Map<String, Object>> getUserMusicList(Map<String, Object> map);

    /**
     * 获取最流行的音乐列表
     *
     * @return list
     */
    List<Map<String, Object>> getPopularMusicList();

    /**
     * 拉取用户喜欢的music列表（分页）
     *
     * @param map userId, start, end
     * @return list
     */
    List<Map<String, Object>> getUserMusicListLike(Map<String, Object> map);

    /**
     * 获取最新的五首音乐
     *
     * @return list
     */
    List<Map<String, Object>> getNewMusicList();

    /**
     * 关键词搜索
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> listByKeyword(Map<String, Object> map);
}
