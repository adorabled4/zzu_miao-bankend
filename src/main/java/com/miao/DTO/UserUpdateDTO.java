package com.miao.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dhx_
 * @className UserUpdateDTO
 * @date : 2022/11/03/ 20:40
 **/
@Data
@ApiModel("用户编辑资料")
public class UserUpdateDTO implements Serializable {
    private static final long serialVersionUID = -7089675522966045615L;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称",required = false)
    private String userName;

    /**
     * 学校
     */
    @ApiModelProperty(value = "用户学校",required = true)
    private String school;

    /**
     * 性别 1 男 0 女
     */
    @ApiModelProperty(value = "用户性别",required = true)
    private int gender;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "用户手机号",required = true)
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "用户邮箱",required = true)
    private String email;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期",required = true)
    private Date birth;
}
