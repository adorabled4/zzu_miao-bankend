package com.miao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 关注表
 * @TableName t_follow
 */
@TableName(value ="t_follow")
@Data
public class Follow implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long followId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 被关注的id
     */
    private Long concernedId;

    /**
     * 1表示关注动物,2表示关注人
     */
    private Integer followType;

    /**
     * 
     */
    private Date createTime;

    /**
     * 逻辑删除 1表示删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 数据更新时间
     */
    private Date updateTime;

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
        Follow other = (Follow) that;
        return (this.getFollowId() == null ? other.getFollowId() == null : this.getFollowId().equals(other.getFollowId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getConcernedId() == null ? other.getConcernedId() == null : this.getConcernedId().equals(other.getConcernedId()))
            && (this.getFollowType() == null ? other.getFollowType() == null : this.getFollowType().equals(other.getFollowType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFollowId() == null) ? 0 : getFollowId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getConcernedId() == null) ? 0 : getConcernedId().hashCode());
        result = prime * result + ((getFollowType() == null) ? 0 : getFollowType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", followId=").append(followId);
        sb.append(", userId=").append(userId);
        sb.append(", concernedId=").append(concernedId);
        sb.append(", followType=").append(followType);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}