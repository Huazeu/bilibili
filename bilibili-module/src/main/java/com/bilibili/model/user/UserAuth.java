package com.bilibili.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 * @TableName user_auth
 */
@TableName(value ="user_auth")
@Data
public class UserAuth {
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
    private String identityType;

    /**
     *
     */
    private String identifier;

    /**
     *
     */
    private String credential;

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
        UserAuth other = (UserAuth) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIdentityType() == null ? other.getIdentityType() == null : this.getIdentityType().equals(other.getIdentityType()))
            && (this.getIdentifier() == null ? other.getIdentifier() == null : this.getIdentifier().equals(other.getIdentifier()))
            && (this.getCredential() == null ? other.getCredential() == null : this.getCredential().equals(other.getCredential()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIdentityType() == null) ? 0 : getIdentityType().hashCode());
        result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
        result = prime * result + ((getCredential() == null) ? 0 : getCredential().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", identityType=").append(identityType);
        sb.append(", identifier=").append(identifier);
        sb.append(", credential=").append(credential);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}
