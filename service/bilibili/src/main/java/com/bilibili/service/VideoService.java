package com.bilibili.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bilibili.model.video.Video;
import com.bilibili.vo.video.VideoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
* @author 22147
* @description 针对表【video】的数据库操作Service
* @createDate 2025-04-15 12:36:24
*/
public interface VideoService extends IService<Video> {


        /**
     * 上传视频文件
     * @param file 要上传的视频文件
     * @return 包含上传结果的Map，通常包含视频URL、上传状态等信息
     */
    Map<String, Object> uploadVideo(MultipartFile file);


    IPage<VideoVO> findVideoPage(Page<Video> videoVoListVoPage, Long categoryId);
}
