package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.UserAuthMapper;
import com.bilibili.model.user.UserAuth;
import com.bilibili.service.UserAuthService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【user_auth】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth>
    implements UserAuthService {

}




