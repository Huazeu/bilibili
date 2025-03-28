package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.model.video.Video;
import com.bilibili.service.VideoService;
import com.bilibili.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【video】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




