package com.miao.DTO;

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

public class AnimalDTO implements Serializable {

    private Long animalId;

    /**
     * 动物名字
     */
    private String animalName;

    /**
     * 物种
     */
    private String species;

    /**
     * 创建者
     */
    private String createUserName;
    /**
     * 动物图片
     */
    private String pictures;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 动物介绍
     */
    private String content;
    /**
     * 出现地址
     */
    private String address;

    /**
     * 关注人数
     */
    private Long followCount;

    /**
     *
     */
    private Date createTime;

}
