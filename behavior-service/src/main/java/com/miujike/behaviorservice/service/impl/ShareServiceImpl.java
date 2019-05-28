package com.miujike.behaviorservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.behaviorservice.domain.Share;
import com.miujike.behaviorservice.mapper.ShareMapper;
import com.miujike.behaviorservice.service.IShareService;
import com.miujike.behaviorservice.service.IUserClient;
import com.miujike.behaviorservice.service.IWorksClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/5/28 11:48 PM
 */
@Service("shareService")
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

    @Autowired
    private IWorksClient worksClient;

    @Autowired
    private IUserClient userClient;

    @Override
    public Share doShare(Share share) {
        share.setCreateTime(new Date());
        boolean success = save(share);
        if (success) {
            boolean s = worksClient.addShareNum(share.getTargetId(), share.getTargetType());
            boolean us = userClient.addEggNum(share.getFromUserId(), share.getEggs());
            return s && us ? share : null;
        }
        return null;
    }
}
