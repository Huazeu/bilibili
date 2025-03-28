package com.bilibili.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bilibili.model.user.User;

/**
* @author 22147
* @description 针对表【user】的数据库操作Service
* @createDate 2025-04-15 12:36:24
*/
public interface UserService extends IService<User> {

    /**
     * 更新用户头像
     * @param userId 用户id
     * @param url 头像url
     */
    void updateAvatar(Long userId, String url);
}
