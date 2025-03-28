package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.CoinAccountMapper;
import com.bilibili.model.user.CoinAccount;
import com.bilibili.service.CoinAccountService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【coin_account】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class CoinAccountServiceImpl extends ServiceImpl<CoinAccountMapper, CoinAccount>
    implements CoinAccountService {

}




