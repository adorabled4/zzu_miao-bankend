package com.miao.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dhx_
 * @className CommentDTO
 * @description TODO
 * @date 2022/10/31 20:53
 */
@Data
@ApiModel("评论基本信息")
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = -1584793452966045615L;
    /**
     *
     */
    @ApiModelProperty(value = "评论的id",required = false)
    private Long commentId;

    /**
     * 回复的 帖子id / 评论id
     */
    @ApiModelProperty(value = "回复的帖子的id",required = true)
    private Long repliedId;

    /**
     *  发布的用户的基本信息
     */
    @ApiModelProperty(value = "评论发布者的基本信息",required = false)
    private UserDTO userDTO;
    /**
     * 评论内容, 注意这个不能为空
     */
    @ApiModelProperty(value = "评论内容",required = true)
    private String commentContent;

    /**
     * 点赞数
     */
    @ApiModelProperty(value = "评论点赞数",required = false)
    private Long likeCount;

    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论发布时间",required = false)
    private Date createTime;

    /**
     * 楼层
     */
    @ApiModelProperty(value = "评论的楼层",required = false)
    private Long floor;

    /**
     * 是否点赞
     */
    @ApiModelProperty(value = "是否点赞了",required = false)
    private Boolean isLike;
}
