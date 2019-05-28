package com.miujike.behaviorservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.behaviorservice.domain.Thumb;

/**
 * @author Ls J
 * @date 2019/4/13 12:10 PM
 */
public interface IThumbService extends IService<Thumb> {

    /**
     * 点赞（送鸡蛋）
     * @param thumb  thumb
     * @return thumb
     */
    Thumb doThumb(Thumb thumb);
}
