package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.UserMapper;
import com.bilibili.model.user.User;
import com.bilibili.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public void updateAvatar(Long userId, String url) {
        User user = this.getById(userId);
        user.setId(userId);
        user.setAvatar(url);
        this.updateById(user);
    }
}




