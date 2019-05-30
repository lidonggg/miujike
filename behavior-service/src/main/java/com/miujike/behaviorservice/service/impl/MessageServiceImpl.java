package com.miujike.behaviorservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miujike.behaviorservice.domain.Message;
import com.miujike.behaviorservice.mapper.MessageMapper;
import com.miujike.behaviorservice.service.IMessageService;
import org.springframework.stereotype.Service;

/**
 * @author Ls J
 * @date 2019/5/30 6:45 PM
 */
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
