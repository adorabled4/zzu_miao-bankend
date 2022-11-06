package com.miao.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dhx_
 * @className AnimalDTO
 * @description
 * @date 2022/10/26 9:20
 */
@Data
@ApiModel("动物基本信息")
public class AnimalDTO implements Serializable {
    private static final long serialVersionUID = -1584653452966045615L;

    @ApiModelProperty(value = "动物id",required = false)
    private Long animalId;

    /**
     * 动物名字
     */
    @ApiModelProperty(value = "动物名字",required = true)
    private String animalName;

    /**
     * 物种
     */
    @ApiModelProperty(value = "动物物种",required = true)
    private String species;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "发布动物的用户的基本信息",required = false)
    private UserDTO userDTO;

    /**
     * 动物图片
     */
    @ApiModelProperty(value = "动物图片",required = false)
    private String pictures;

    /**
     * 性别
     */
    @ApiModelProperty(value = "动物性别",required = false)
    private int gender;

    /**
     * 动物介绍
     */
    @ApiModelProperty(value = "动物介绍",required = true)
    private String content;
    /**
     * 出现地址
     */
    @ApiModelProperty(value = "出现地址",required = true)
    private String address;

    /**
     * 关注人数
     */
    @ApiModelProperty(value = "关注人数",required = false)
    private Long followCount;

    /**
     *
     */
    @ApiModelProperty(value = "发布时间",required = false)
    private Date createTime;

}
