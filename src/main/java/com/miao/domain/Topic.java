package com.miao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

//    /**
//     * 标签id
//     */
//    private Long tagId;

    /**
     * 帖子标题
     */
    private String topicTitle;

    /**
     * 帖子内容
     */
    private String topicContent;

//    /**
//     * 帖子内的图片
//     */
//    private String pictureUrls;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return Objects.equals(getTopicId(), topic.getTopicId()) && Objects.equals(getUserId(), topic.getUserId()) && Objects.equals(getTopicTitle(), topic.getTopicTitle()) && Objects.equals(getTopicContent(), topic.getTopicContent()) && Objects.equals(getCommentCount(), topic.getCommentCount()) && Objects.equals(getLikeCount(), topic.getLikeCount()) && Objects.equals(getIsSelected(), topic.getIsSelected()) && Objects.equals(getCreateTime(), topic.getCreateTime()) && Objects.equals(getIsDelete(), topic.getIsDelete()) && Objects.equals(getUpdateTime(), topic.getUpdateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTopicId(), getUserId(), getTopicTitle(), getTopicContent(), getCommentCount(), getLikeCount(), getIsSelected(), getCreateTime(), getIsDelete(), getUpdateTime());
    }
}