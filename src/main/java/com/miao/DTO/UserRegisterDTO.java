package com.miao.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Dhx_
 * @className UserRegisterRequest
 * @description TODO 用户注册请求体 implements Serializable防止序列化冲突
 * @date 2022/10/22 17:22
 */
@Data
@ApiModel("用户注册")
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = -7089675522966087515L;
    @ApiModelProperty(value = "用户账户",required = true)
    private String userAccount;


    @ApiModelProperty(value = "用户密码",required = true)
    private String userPassword;


    @ApiModelProperty(value = "确认密码",required = true)
    private String checkPassword;

}