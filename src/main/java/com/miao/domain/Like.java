package com.miao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 点赞表
 * @TableName t_like
 */
@TableName(value ="t_like")
@Data
public class Like implements Serializable {
    /**
     * 点赞的id
     */
    @TableId(type = IdType.AUTO)
    private Long likeId;

    /**
     * 点赞的用户的id
     */
    private Long userId;

    /**
     * 被点赞的帖子 or 评论的id
     */
    private Long likedId;

    /**
     * type表示回复的是帖子还是评论  1表示帖子 , 2 表示评论
     */
    private Integer likeType;

    /**
     * 点赞时间
     */
    private Date createTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        Like other = (Like) that;
        return (this.getLikeId() == null ? other.getLikeId() == null : this.getLikeId().equals(other.getLikeId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLikedId() == null ? other.getLikedId() == null : this.getLikedId().equals(other.getLikedId()))
            && (this.getLikeType() == null ? other.getLikeType() == null : this.getLikeType().equals(other.getLikeType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLikeId() == null) ? 0 : getLikeId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLikedId() == null) ? 0 : getLikedId().hashCode());
        result = prime * result + ((getLikeType() == null) ? 0 : getLikeType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", likeId=").append(likeId);
        sb.append(", userId=").append(userId);
        sb.append(", likedId=").append(likedId);
        sb.append(", likeType=").append(likeType);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}