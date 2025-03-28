package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.PaymentMapper;
import com.bilibili.model.user.Payment;
import com.bilibili.service.PaymentService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【payment】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
    implements PaymentService {

}




