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
     * 拉取用户video列表（分页）
     * @param map userId, start, end
     * @return list
     */
    List<Map<String,Object>> getUserVideoList(Map<String,Object> map);

    /**
     * 获取最新的五个视频
     * @return  List<Video>
     */
    List<Video> getNewVideoList();
}
