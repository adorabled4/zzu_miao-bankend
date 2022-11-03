package com.miao.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author Dhx_
 * @className CommentDTO
 * @description TODO
 * @date 2022/10/31 20:53
 */
@Data
public class CommentDTO {
    /**
     *
     */
    private Long commentId;

    /**
     * 回复的 帖子id / 评论id
     */
    private Long repliedId;

    /**
     *  发布的用户的基本信息
     */
    private UserDTO userDTO;
    /**
     * 评论内容, 注意这个不能为空
     */
    private String commentContent;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论时间
     */
    private Date createTime;
}
