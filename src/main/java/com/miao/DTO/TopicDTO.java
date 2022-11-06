package com.miao.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dhx_
 * @className TopicDTO
 * @description TODO
 * @date 2022/10/26 20:58
 */
@Data
@ApiModel("帖子信息")
public class TopicDTO  implements Serializable {
    private static final long serialVersionUID = -7084793452966045615L;

    /**
     * 帖子标题
     */
    @ApiModelProperty(value = "帖子的标题",required = true)
    private String topicTitle;

    /**
     * 帖子内容
     */
    @ApiModelProperty(value = "帖子的内容",required = true)
    private String topicContent;

    /**
     * 标签
     */
    @ApiModelProperty(value = "帖子的标签(多个,使用','拼接 )",required = false)
    private String tags;

//    /**
//     * 作者名称 => 已经在userDTO中存储了
//     */
//    @ApiModelProperty(value = "帖子的作者的名称",required = false)
//    private String author;


    /**
     * 评论总数
     */
    @ApiModelProperty(value = "评论个数",required = false)
    private Long commentCount;

    /**
     * 点赞总数
     */
    @ApiModelProperty(value = "点赞个数",required = false)
    private Long likeCount;

    /**
     * 是否精选
     */
    @ApiModelProperty(value = "是否精选",required = false)
    private Integer isSelected;

    /**
     * 帖子的创建时间
     */
    @ApiModelProperty(value = "帖子的发布时间",required = false)
    private Date createTime;

    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "发布的用户的基本信息",required = false)
    private UserDTO userDTO;

    /**
     * 是否点赞
     */
    @ApiModelProperty(value = "是否点赞了",required = false)
    private Boolean isLike;
}

