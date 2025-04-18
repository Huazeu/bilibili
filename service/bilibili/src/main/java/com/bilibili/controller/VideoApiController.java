/**
 * @ Tool：IntelliJ IDEA
 * @ Author：wkl
 * @ Date：2025-04-17-23:54
 * @ Version：1.0
 * @ Description：视频上传控制器
 */

package com.bilibili.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bilibili.model.video.Video;
import com.bilibili.result.Result;
import com.bilibili.service.VideoService;
import com.bilibili.vo.video.VideoVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@RequestMapping("api/video")
@RequiredArgsConstructor
@RestController
public class VideoApiController {
    private  final VideoService videoService;
    @Operation(summary = "上传视频")
    @PostMapping("uploadVideo")
    public Result uploadTrack(MultipartFile file){
        Map<String,Object> map= videoService.uploadVideo(file);
        return  Result.ok(map);
	}
//    @SaCheckLogin

    /**
     *
     * @param categoryId 分类id
     * @param page 页码
     * @param limit 每页显示数量
     * @return
     */
    @Operation(summary = "根据分类Id获取视频分页列表")
    @GetMapping("videoPage/{categoryId}/{page}/{limit}")
    public Result ckPage(@PathVariable Long categoryId, @PathVariable Long page, @PathVariable Long limit) {
        //  获取用户Id
//        Long userId = StpUtil.getLoginIdAsLong();
        //  创建Page 对象

        Page<Video> VideoVoListVoPage = new Page<>(page,limit);
        //  调用服务层方法.
        IPage<VideoVO> VideoListVoIPage = videoService.findVideoPage(VideoVoListVoPage, categoryId);
        //  返回数据
        return Result.ok(VideoListVoIPage);
	}
}
