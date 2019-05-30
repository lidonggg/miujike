package com.miujike.worksservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miujike.worksservice.domain.Video;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/4/10 6:38 PM
 */
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 获取某条视频的信息
     *
     * @param videoId videoId
     * @return map
     */
    Map<String, Object> getVideoInfo(long videoId);

    /**
     * 拉取用户video列表（分页）
     *
     * @param map userId, start, end
     * @return list
     */
    List<Map<String, Object>> getUserVideoList(Map<String, Object> map);

    /**
     * 拉取用户喜欢的video列表（分页）
     *
     * @param map userId, start, end
     * @return list
     */
    List<Map<String, Object>> getUserVideoListLike(Map<String, Object> map);

    /**
     * 获取最新的视频
     *
     * @param n
     * @return list
     */
    List<Map<String, Object>> getNewVideoList(int n);

    /**
     * 获取最新的官方视频
     *
     * @param n
     * @return list
     */
    List<Map<String, Object>> getOfficialNewVideoList(int n);


    /**
     * 随机n条数据，作为推荐
     *
     * @param map
     * @return list
     */
    List<Map<String, Object>> getRecommendVideoList(Map<String, Object> map);

    /**
     * 关键词搜索
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> listByKeyword(Map<String, Object> map);
}
