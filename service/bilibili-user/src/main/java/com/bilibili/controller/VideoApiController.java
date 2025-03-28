package com.bilibili.controller;

import bilibili.exception.BlibiliException;
import bilibili.result.Result;
import bilibili.result.ResultCodeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bilibili.DTO.videoDTO.VideoDTO;
import com.bilibili.model.video.Video;
import com.bilibili.service.VideoService;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/video")
@Tag(name = "视频管理", description = "视频相关接口")
@Validated
@Slf4j
public class VideoApiController {

    @Autowired
    private VideoService videoService;

    /**
     * 添加视频
     * 由当前登录用户创建视频
     */
    @Operation(summary = "添加视频", description = "添加新的视频信息")
    @PostMapping("/add")
    @SaCheckLogin  // 确保当前用户已登录
    public Result addVideo(@RequestBody @Valid VideoDTO videoDTO) {
        Long userId = StpUtil.getLoginIdAsLong(); // 获取当前登录的用户ID

        // 创建视频对象并设置当前用户为上传者
        Video video = new Video();
        video.setUserId(userId); // 设置上传视频的用户ID
        video.setTitle(videoDTO.getTitle());
        video.setDescription(videoDTO.getDescription());
        video.setCoverUrl(videoDTO.getCoverUrl());
        video.setVideoUrl(videoDTO.getVideoUrl());
        video.setDuration(videoDTO.getDuration());
        video.setCategoryId(videoDTO.getCategoryId());
        video.setTags(videoDTO.getTags());
        video.setBulletScreenCount(videoDTO.getBulletScreenCount());
        video.setCopyright(videoDTO.getCopyright());
        video.setSource(videoDTO.getSource());
        video.setStatus(0); // 默认审核中
        video.setCreatedAt(LocalDateTime.now());
        video.setUpdatedAt(LocalDateTime.now());

        // 保存视频记录
        videoService.save(video);
        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新视频信息
     * 仅允许当前用户更新自己的视频
     */
    @Operation(summary = "更新视频", description = "更新视频信息")
    @PutMapping("/update")
    @SaCheckLogin  // 确保当前用户已登录
    public Result updateVideo(@RequestBody @Valid VideoDTO videoDTO) {
        Long userId = StpUtil.getLoginIdAsLong(); // 获取当前登录的用户ID

        Video video = videoService.getById(videoDTO.getId());
        if (video == null) {
            return Result.fail(ResultCodeEnum.VIDEO_NOT_EXIST);
        }

        // 检查当前用户是否是视频的上传者
        if (!video.getUserId().equals(userId)) {
            return Result.fail(ResultCodeEnum.PERMISSION);
        }

        // 更新视频内容
        video.setTitle(videoDTO.getTitle());
        video.setDescription(videoDTO.getDescription());
        video.setCoverUrl(videoDTO.getCoverUrl());
        video.setVideoUrl(videoDTO.getVideoUrl());
        video.setDuration(videoDTO.getDuration());
        video.setCategoryId(videoDTO.getCategoryId());
        video.setTags(videoDTO.getTags());
        video.setBulletScreenCount(videoDTO.getBulletScreenCount());
        video.setCopyright(videoDTO.getCopyright());
        video.setSource(videoDTO.getSource());
        video.setStatus(videoDTO.getStatus());
        video.setUpdatedAt(LocalDateTime.now());

        // 更新视频记录
        videoService.updateById(video);
        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除视频
     * 仅允许当前用户删除自己的视频
     */
    @Operation(summary = "删除视频", description = "根据视频ID删除视频")
    @DeleteMapping("/delete/{id}")
    @SaCheckLogin  // 确保当前用户已登录
    public Result deleteVideo(@PathVariable Long id) {
        Long userId = StpUtil.getLoginIdAsLong(); // 获取当前登录的用户ID

        Video video = videoService.getById(id);
        if (video == null) {
            return Result.fail(ResultCodeEnum.VIDEO_DELETED);
        }

        // 检查当前用户是否是视频的上传者
        if (!video.getUserId().equals(userId)) {
            return Result.fail(ResultCodeEnum.PERMISSION);
        }

        // 删除视频记录
        videoService.removeById(id);
        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询视频信息
     */
    @Operation(summary = "查询视频", description = "根据视频ID查询视频信息")
    @GetMapping("/get/{id}")
    public Result<VideoDTO> getVideo(@PathVariable Long id) {
        Video video = videoService.getById(id);
        if (video == null) {
            throw  new BlibiliException(ResultCodeEnum.VIDEO_NOT_EXIST);
        }

        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(video.getId());
        videoDTO.setUserId(video.getUserId());
        videoDTO.setTitle(video.getTitle());
        videoDTO.setDescription(video.getDescription());
        videoDTO.setCoverUrl(video.getCoverUrl());
        videoDTO.setVideoUrl(video.getVideoUrl());
        videoDTO.setDuration(video.getDuration());
        videoDTO.setCategoryId(video.getCategoryId());
        videoDTO.setTags(video.getTags());
        videoDTO.setBulletScreenCount(video.getBulletScreenCount());
        videoDTO.setCopyright(video.getCopyright());
        videoDTO.setSource(video.getSource());
        videoDTO.setStatus(video.getStatus());
        videoDTO.setCreatedAt(video.getCreatedAt());
        videoDTO.setUpdatedAt(video.getUpdatedAt());

        return Result.ok(videoDTO);
    }

    /**
     * 获取所有视频
     */
    @Operation(summary = "获取所有视频", description = "获取所有视频的列表")
    @GetMapping("/list/all")
    public Result listVideos() {
        return Result.ok(videoService.list());
    }
    /**
     * 分页查询视频信息
     */
    @Operation(summary = "分页查询视频", description = "根据分页参数查询视频列表")
    @GetMapping("/list")
    public Result<Page<VideoDTO>> listVideos(
            @RequestParam(defaultValue = "1") int page,     // 当前页
            @RequestParam(defaultValue = "10") int pageSize) { // 每页大小

        // 创建分页对象
        Page<Video> videoPage = new Page<>(page, pageSize);

        // 执行分页查询
        Page<Video> result = videoService.page(videoPage);

        // 转换为DTO对象
        Page<VideoDTO> videoDTOPage = new Page<>();
        videoDTOPage.setCurrent(result.getCurrent());
        videoDTOPage.setSize(result.getSize());
        videoDTOPage.setTotal(result.getTotal());
        videoDTOPage.setRecords(convertToDTO(result.getRecords()));

        return Result.ok(videoDTOPage);
    }

    /**
     * 使用BeanUtils将Video对象转换为VideoDTO对象
     */
    private List<VideoDTO> convertToDTO(List<Video> videos) {
        // 使用BeanUtils将Video转换为VideoDTO
        return videos.stream().map(video -> {
            VideoDTO videoDTO = new VideoDTO();
            BeanUtils.copyProperties(video, videoDTO); // 使用BeanUtils进行属性复制
            return videoDTO;
        }).toList();
    }
}
