package com.bilibili.model.video;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @TableName video
 */
@TableName(value ="video")
@Data
public class Video {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String coverUrl;

    /**
     *
     */
    private String videoUrl;

    /**
     *
     */
    private Integer duration;

    /**
     *
     */
    private Long categoryId;

    /**
     *
     */
    private String tags;

    /**
     * 弹幕数
     */
    private Integer bulletScreenCount;

    /**
     * 版权信息
     */
    private String copyright;

    /**
     * 来源（原创/转载）
     */
    private String source;

    /**
     * 0-待审核, 1-审核通过, 2-已发布, 3-已下架, 4-审核失败
     */
    private Integer status;

    /**
     *
     */
    private LocalDateTime createdAt;

    /**
     *
     */
    private LocalDateTime updatedAt;

    /**
     * 0-未删除, 1-已删除
     */
    private Integer isDeleted;

    /**
     * 视频分辨率，如1080p
     */
    private String resolution;

    /**
     * 视频文件大小（字节）
     */
    private Long fileSize;

    /**
     * 0-不可用, 1-可用
     */
    private Integer isActive;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 0-未开始, 1-上传中, 2-上传完成, 3-合并完成, 4-上传失败
     */
    private Integer uploadStatus;

    /**
     * 总分片数
     */
    private Integer chunkTotal;

    /**
     * 已上传分片数
     */
    private Integer chunkUploaded;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Video other = (Video) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCoverUrl() == null ? other.getCoverUrl() == null : this.getCoverUrl().equals(other.getCoverUrl()))
            && (this.getVideoUrl() == null ? other.getVideoUrl() == null : this.getVideoUrl().equals(other.getVideoUrl()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getBulletScreenCount() == null ? other.getBulletScreenCount() == null : this.getBulletScreenCount().equals(other.getBulletScreenCount()))
            && (this.getCopyright() == null ? other.getCopyright() == null : this.getCopyright().equals(other.getCopyright()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getResolution() == null ? other.getResolution() == null : this.getResolution().equals(other.getResolution()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
            && (this.getDeletedAt() == null ? other.getDeletedAt() == null : this.getDeletedAt().equals(other.getDeletedAt()))
            && (this.getUploadStatus() == null ? other.getUploadStatus() == null : this.getUploadStatus().equals(other.getUploadStatus()))
            && (this.getChunkTotal() == null ? other.getChunkTotal() == null : this.getChunkTotal().equals(other.getChunkTotal()))
            && (this.getChunkUploaded() == null ? other.getChunkUploaded() == null : this.getChunkUploaded().equals(other.getChunkUploaded()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCoverUrl() == null) ? 0 : getCoverUrl().hashCode());
        result = prime * result + ((getVideoUrl() == null) ? 0 : getVideoUrl().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getBulletScreenCount() == null) ? 0 : getBulletScreenCount().hashCode());
        result = prime * result + ((getCopyright() == null) ? 0 : getCopyright().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getResolution() == null) ? 0 : getResolution().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getDeletedAt() == null) ? 0 : getDeletedAt().hashCode());
        result = prime * result + ((getUploadStatus() == null) ? 0 : getUploadStatus().hashCode());
        result = prime * result + ((getChunkTotal() == null) ? 0 : getChunkTotal().hashCode());
        result = prime * result + ((getChunkUploaded() == null) ? 0 : getChunkUploaded().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", coverUrl=").append(coverUrl);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", duration=").append(duration);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", tags=").append(tags);
        sb.append(", bulletScreenCount=").append(bulletScreenCount);
        sb.append(", copyright=").append(copyright);
        sb.append(", source=").append(source);
        sb.append(", status=").append(status);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", resolution=").append(resolution);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", isActive=").append(isActive);
        sb.append(", deletedAt=").append(deletedAt);
        sb.append(", uploadStatus=").append(uploadStatus);
        sb.append(", chunkTotal=").append(chunkTotal);
        sb.append(", chunkUploaded=").append(chunkUploaded);
        sb.append("]");
        return sb.toString();
    }
}
