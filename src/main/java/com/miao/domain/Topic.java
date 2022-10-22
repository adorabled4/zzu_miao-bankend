package com.miao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_topic
 */
@TableName(value ="t_topic")
@Data
public class Topic implements Serializable {
    /**
     * 帖子id
     */
    @TableId(type = IdType.AUTO)
    private Long topicId;

    /**
     * 发帖用户的id
     */
    private Long userId;

    /**
     * 标签id
     */
    private Long tagId;

    /**
     * 帖子标题
     */
    private String topicTitle;

    /**
     * 帖子内容
     */
    private String topicContent;

    /**
     * 评论总数
     */
    private Long commentCount;

    /**
     * 点赞总数
     */
    private Long likeCount;

    /**
     * 是否精选
     */
    private Integer isSelected;

    /**
     * 帖子发布时间
     */
    private Date createTime;

    /**
     * 逻辑删除 1表示删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 话题更新时间
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
        Topic other = (Topic) that;
        return (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
            && (this.getTopicTitle() == null ? other.getTopicTitle() == null : this.getTopicTitle().equals(other.getTopicTitle()))
            && (this.getTopicContent() == null ? other.getTopicContent() == null : this.getTopicContent().equals(other.getTopicContent()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
            && (this.getLikeCount() == null ? other.getLikeCount() == null : this.getLikeCount().equals(other.getLikeCount()))
            && (this.getIsSelected() == null ? other.getIsSelected() == null : this.getIsSelected().equals(other.getIsSelected()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getTopicTitle() == null) ? 0 : getTopicTitle().hashCode());
        result = prime * result + ((getTopicContent() == null) ? 0 : getTopicContent().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getLikeCount() == null) ? 0 : getLikeCount().hashCode());
        result = prime * result + ((getIsSelected() == null) ? 0 : getIsSelected().hashCode());
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
        sb.append(", topicId=").append(topicId);
        sb.append(", userId=").append(userId);
        sb.append(", tagId=").append(tagId);
        sb.append(", topicTitle=").append(topicTitle);
        sb.append(", topicContent=").append(topicContent);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", isSelected=").append(isSelected);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}