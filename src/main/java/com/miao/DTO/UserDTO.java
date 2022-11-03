package com.miao.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Dhx_
 * @className UserDTO
 * @description TODO
 * @date 2022/10/22 16:44
 */
@Data
@ApiModel("基本用户信息")
public class UserDTO  implements Serializable {
    private static final long serialVersionUID = -7089653452966045615L;

    @ApiModelProperty(value = "用户id",required = false)
    private Long userId;


    @ApiModelProperty(value = "用户昵称",required = false)
    private String userName;


    @ApiModelProperty(value = "用户头像url",required = false)
    private String avatarUrl;

    @ApiModelProperty(value = "权限 1为管理员",required = false)
    private int isAdmin;
}
