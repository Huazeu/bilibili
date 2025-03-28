package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.MembershipMapper;
import com.bilibili.model.user.Membership;
import com.bilibili.service.MembershipService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【membership】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class MembershipServiceImpl extends ServiceImpl<MembershipMapper, Membership>
    implements MembershipService {

}




