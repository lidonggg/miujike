package com.miujike.behaviorservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.behaviorservice.domain.Thumb;
import com.miujike.behaviorservice.mapper.ThumbMapper;
import com.miujike.behaviorservice.service.IThumbService;
import com.miujike.behaviorservice.service.IUserClient;
import com.miujike.behaviorservice.service.IWorksClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 12:11 PM
 */
@Service("thumbService")
public class ThumbServiceImpl extends ServiceImpl<ThumbMapper, Thumb> implements IThumbService {

    @Autowired
    private IWorksClient worksClient;

    @Autowired
    private IUserClient userClient;

    @Override
    public Thumb doThumb(Thumb thumb) {
        boolean us = userClient.subtractEggNum(thumb.getFromUserId(), thumb.getEggs());
        if (us) {
            QueryWrapper<Thumb> thumbWrapper = new QueryWrapper<>();
            thumbWrapper.eq("target_id", thumb.getTargetId())
                    .eq("from_user_id", thumb.getFromUserId())
                    .eq("target_type", thumb.getTargetType());
            Thumb exit = getOne(thumbWrapper);
            if (null == exit) {
                exit = thumb;
                exit.setEggs(0);
            }
            exit.setCreateTime(new Date());
            exit.setEggs(exit.getEggs() + 1);
            boolean success = saveOrUpdate(exit);
            if (success) {
                boolean s = worksClient.addThumbNum(thumb.getTargetId(), thumb.getTargetType());
                return s ? thumb : null;
            }
            return null;
        }

        return null;
    }
}
