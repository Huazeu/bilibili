package com.bilibili.model.video;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @TableName video_chunk
 */
@TableName(value ="video_chunk")
@Data
public class VideoChunk {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long videoId;

    /**
     * 分片序号，从0开始
     */
    private Integer chunkIndex;

    /**
     * 分片大小（字节）
     */
    private Long chunkSize;

    /**
     * 分片哈希值，用于校验
     */
    private String chunkHash;

    /**
     * 0-未上传, 1-已上传, 2-上传失败
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
        VideoChunk other = (VideoChunk) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getChunkIndex() == null ? other.getChunkIndex() == null : this.getChunkIndex().equals(other.getChunkIndex()))
            && (this.getChunkSize() == null ? other.getChunkSize() == null : this.getChunkSize().equals(other.getChunkSize()))
            && (this.getChunkHash() == null ? other.getChunkHash() == null : this.getChunkHash().equals(other.getChunkHash()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getChunkIndex() == null) ? 0 : getChunkIndex().hashCode());
        result = prime * result + ((getChunkSize() == null) ? 0 : getChunkSize().hashCode());
        result = prime * result + ((getChunkHash() == null) ? 0 : getChunkHash().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoId=").append(videoId);
        sb.append(", chunkIndex=").append(chunkIndex);
        sb.append(", chunkSize=").append(chunkSize);
        sb.append(", chunkHash=").append(chunkHash);
        sb.append(", status=").append(status);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}
