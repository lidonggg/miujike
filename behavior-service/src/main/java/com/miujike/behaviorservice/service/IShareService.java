package com.miujike.behaviorservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miujike.behaviorservice.domain.Share;

/**
 * @author Ls J
 * @date 2019/5/28 11:48 PM
 */
public interface IShareService extends IService<Share> {

    /**
     * 分享
     * @param share  share
     * @return share
     */
    Share doShare(Share share);
}
