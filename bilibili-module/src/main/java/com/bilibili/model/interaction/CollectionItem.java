package com.bilibili.model.interaction;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @TableName collection_item
 */
@TableName(value ="collection_item")
@Data
public class CollectionItem {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long folderId;

    /**
     *
     */
    private Long videoId;

    /**
     *
     */
    private LocalDateTime createdAt;

    /**
     * 0-未删除, 1-已删除
     */
    private Integer isDeleted;

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
        CollectionItem other = (CollectionItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFolderId() == null ? other.getFolderId() == null : this.getFolderId().equals(other.getFolderId()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFolderId() == null) ? 0 : getFolderId().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", folderId=").append(folderId);
        sb.append(", videoId=").append(videoId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}
