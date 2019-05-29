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
     * @param videoId videoId
     * @return map
     */
    Map<String,Object> getVideoInfo(long videoId);

    /**
     * 拉取用户video列表（分页）
     * @param map userId, start, end
     * @return list
     */
    List<Map<String,Object>> getUserVideoList(Map<String,Object> map);

    /**
     * 获取最新的五个视频
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getNewVideoList();

    /**
     * 随机n条数据，作为推荐
     * @param map
     * @return list
     */
    List<Map<String,Object>> getRecommendVideoList(Map<String,Object> map);
}
