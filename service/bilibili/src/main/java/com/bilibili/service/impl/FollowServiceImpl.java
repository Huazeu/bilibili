package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.FollowMapper;
import com.bilibili.model.user.Follow;
import com.bilibili.service.FollowService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【follow】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow>
    implements FollowService {

}




