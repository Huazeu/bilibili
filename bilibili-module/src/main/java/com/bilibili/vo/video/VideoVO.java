package com.bilibili.vo.video;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoVO {
    private Long id; // 视频ID
    private Long userId; // 用户ID
    private String title; // 视频标题
    private String description; // 视频描述
    private String coverUrl; // 封面URL
    private String videoUrl; // 视频URL
    private Integer duration; // 视频时长（秒）
    private String tags; // 视频标签
    private Integer bulletScreenCount; // 弹幕数量
    private String copyright; // 版权信息
    private String source; // 视频来源
    private Integer status; // 视频状态
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
    private String resolution; // 视频分辨率
    private Long fileSize; // 文件大小（字节）
    private Integer isActive; // 是否激活

    // 构造函数、getter和setter由Lombok自动生成
}

