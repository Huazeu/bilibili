package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.model.user.Membership;
import com.bilibili.service.MembershipService;
import com.bilibili.mapper.MembershipMapper;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【membership】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
public class MembershipServiceImpl extends ServiceImpl<MembershipMapper, Membership>
    implements MembershipService{

}




