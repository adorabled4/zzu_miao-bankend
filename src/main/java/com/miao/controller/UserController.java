package com.miao.controller;

import com.miao.DTO.Result;
import com.miao.DTO.UserRegisterDTO;
import com.miao.common.BaseResponse;
import com.miao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dhx_
 * @className UserController
 * @description TODO
 * @date 2022/10/22 16:20
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 发送验证码
     * @param phone 手机号
     */
    @PostMapping("/code")
    public BaseResponse sendCode(@RequestParam("phone")String phone){
        return userService.sendCode(phone);
    }


    /**
     * 注册功能
     * @param userRegisterDTO 封装信息到userRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public BaseResponse register(UserRegisterDTO userRegisterDTO){
        String userAccount=userRegisterDTO.getUserAccount();
        String userPassword = userRegisterDTO.getUserPassword();
        String checkPassword = userRegisterDTO.getCheckPassword();
        String stuCode=userRegisterDTO.getStuCode();
        return userService.register(userAccount,userPassword,checkPassword,stuCode);
    }

    /**
     * 普通登录 输入用户名以及密码登录
     * @param userAccount
     * @param userPassword
     * @return
     */
    @PostMapping("/login")
    public BaseResponse login(@RequestParam("userAccount")String userAccount,@RequestParam("userPassword")String userPassword){
        return userService.login(userAccount,userPassword);
    }
}
