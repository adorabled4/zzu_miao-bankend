package com.miao.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author dhx_
 * @className UserUpdateDTO
 * @date : 2022/11/03/ 20:40
 **/
@Data
public class UserUpdateDTO {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 学校
     */
    private String school;

    /**
     * 性别 1 男 0 女
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 出生日期
     */
    private Date birth;
}
