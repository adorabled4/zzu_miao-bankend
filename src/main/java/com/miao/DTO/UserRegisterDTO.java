package com.miao.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Dhx_
 * @className UserRegisterRequest
 * @description TODO 用户注册请求体 implements Serializable防止序列化冲突
 * @date 2022/10/22 17:22
 */
@Data
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = -7089675522966087515L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}