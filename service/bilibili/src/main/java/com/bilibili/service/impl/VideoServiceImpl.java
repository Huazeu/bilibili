package com.bilibili.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.config.VodConstantProperties;
import com.bilibili.exception.BlibiliException;
import com.bilibili.mapper.VideoMapper;
import com.bilibili.model.video.Video;
import com.bilibili.service.VideoService;
import com.bilibili.util.UploadFileUtil;
import com.bilibili.vo.video.VideoVO;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author 22147
* @description 针对表【video】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
@RequiredArgsConstructor
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{
    private final VodConstantProperties vodConstantProperties;
    @Override
    public Map<String, Object> uploadVideo(MultipartFile file) {
        //本质是创建map集合
        Map<String,Object> map=new HashMap<>();
        //初始化上传客户端对象
        String tempPath = UploadFileUtil.uploadTempPath(vodConstantProperties.getTempPath(), file);
        VodUploadClient client = new VodUploadClient(vodConstantProperties.getSecretId(), vodConstantProperties.getSecretKey());
        //获取上传请求对象
        VodUploadRequest request = new VodUploadRequest();
        if (file.getContentType().contains("mkv")) {
            request.setMediaType("video/mp4");
        }
        request.setMediaFilePath(tempPath);
        //云点播，任务流 要花钱，存储功能要花钱，有高级功能携带封面上传等等 指定任务流
        try {
            VodUploadResponse response = client.upload(vodConstantProperties.getRegion(), request);
//            LogSF.warn("Upload FileId = {}", response.getFileId());
            map.put("mediaFileId",response.getFileId());
            map.put("mediaUrl",response.getMediaUrl());
            return map;
        } catch (Exception e) {
            log.error("Upload Err", e);
            throw  new BlibiliException(20001,"上传视频失败");
            // 业务方进行异常处理
        }
//		return map; 不可达代码异常。
	}

    @Override
public IPage<VideoVO> findVideoPage(Page<Video> page, Long categoryId) {
    QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
    if (categoryId != null) {
        queryWrapper.eq("category_id", categoryId);

    }
        Page<Video> videoPage = baseMapper.selectPage(page, queryWrapper);
        Page<VideoVO> videoVOPage = new Page<>(videoPage.getCurrent(), videoPage.getSize(), videoPage.getTotal());
        List<VideoVO> videoVOList = videoPage.getRecords().stream().map(video -> {
         VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(video, videoVO);
            return videoVO;
        }).collect(Collectors.toList());   ;
        videoVOPage.setRecords(videoVOList);
        return videoVOPage;
}


}




