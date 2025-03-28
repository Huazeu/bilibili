package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.HistoryMapper;
import com.bilibili.model.interaction.History;
import com.bilibili.service.HistoryService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【history】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History>
    implements HistoryService {

}




