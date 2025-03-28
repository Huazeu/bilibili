package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.UserVideoInteractionMapper;
import com.bilibili.model.interaction.UserVideoInteraction;
import com.bilibili.service.UserVideoInteractionService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【user_video_interaction】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class UserVideoInteractionServiceImpl extends ServiceImpl<UserVideoInteractionMapper, UserVideoInteraction>
    implements UserVideoInteractionService {

}




