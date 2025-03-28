package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.MessageMapper;
import com.bilibili.model.chatmessage.Message;
import com.bilibili.service.MessageService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【message】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService {

}




