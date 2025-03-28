package com.bilibili.DTO.videoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoDTO {

    private Long id;

    private Long userId;

    @NotBlank(message = "视频标题不能为空")
    private String title;

    private String description;

    @NotBlank(message = "封面URL不能为空")
    private String coverUrl;

    @NotBlank(message = "视频URL不能为空")
    private String videoUrl;

    private Integer duration;

    @NotNull(message = "分类ID不能为空")
    private Long categoryId;

    private String tags;

    private Integer bulletScreenCount;

    private String copyright;

    private String source;

    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
