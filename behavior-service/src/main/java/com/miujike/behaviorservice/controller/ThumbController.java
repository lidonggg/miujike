package com.miujike.behaviorservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miujike.behaviorservice.domain.Thumb;
import com.miujike.behaviorservice.service.IThumbService;
import com.miujike.common.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/4/13 12:34 PM
 */
@RestController
@RequestMapping("api/v1/behavior/thumb")
public class ThumbController {

    @Autowired
    private IThumbService thumbService;

    @PostMapping("doThumb")
    public ResponseData<Integer> doThumb(long userId, long targetId, int type) {
        Thumb thumb;

        QueryWrapper<Thumb> thumbWrapper = new QueryWrapper<>();
        thumbWrapper.lambda().eq(Thumb::getFromUserId, userId)
                .eq(Thumb::getTargetId, targetId)
                .eq(Thumb::getTargetType, type);

        thumb = thumbService.getOne(thumbWrapper);
        if (null == thumb) {
            thumb = new Thumb();
            thumb.setCreateTime(new Date());
            thumb.setFromUserId(userId);
            thumb.setTargetId(targetId);
            thumb.setTargetType(type);
            boolean success = thumbService.save(thumb);
            //TODO 添加点赞人数（user/works）
            return new ResponseData<>(success ? 1 : 0);
        }
        return new ResponseData<>(2);
    }

}
