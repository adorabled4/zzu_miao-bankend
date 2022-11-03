package com.miao.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author Dhx_
 * @className TopicDTO
 * @description TODO
 * @date 2022/10/26 20:58
 */
@Data
public class TopicDTO {
    /**
     * 帖子标题
     */
    private String topicTitle;

    /**
     * 帖子内容
     */
    private String topicContent;

    /**
     * 标签
     */
    private String tags;

    /**
     * 作者名称
     */
    private String author;
    /**
     * 帖子的创建时间
     */
    private Date createTime;

    /**
     * 用户基本信息
     */
    private UserDTO userDTO;
}

