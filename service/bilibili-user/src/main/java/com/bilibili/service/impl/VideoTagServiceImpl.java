package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.VideoTagMapper;
import com.bilibili.model.video.VideoTag;
import com.bilibili.service.VideoTagService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【video_tag】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class VideoTagServiceImpl extends ServiceImpl<VideoTagMapper, VideoTag>
    implements VideoTagService {

}




