package com.miao.DTO;

import lombok.Data;

/**
 * @author Dhx_
 * @className AnimalDTO
 * @description TODO
 * @date 2022/10/25 23:28
 */
@Data
public class AnimalDTO {
    /**
     * 动物名字
     */
    private String animalName;

    /**
     * 物种
     */
    private String species;

    /**
     * 动物图片
     */
    private String pictures;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出现地址
     */
    private String address;

    /**
     * 关注人数
     */
    private Long followCount;

}
