package com.miujike.behaviorservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.behaviorservice.domain.Thumb;
import com.miujike.behaviorservice.mapper.ThumbMapper;
import com.miujike.behaviorservice.service.IThumbService;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/4/13 12:11 PM
 */
@Service("thumbService")
public class ThumbServiceImpl extends ServiceImpl<ThumbMapper, Thumb> implements IThumbService {
}
