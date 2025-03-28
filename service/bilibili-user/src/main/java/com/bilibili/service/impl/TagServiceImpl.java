package com.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.model.video.Tag;
import com.bilibili.mapper.TagMapper;
import com.bilibili.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




