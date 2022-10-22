package com.miao.controller;

import com.miao.DTO.Result;
import com.miao.request.UserRegisterRequest;
import com.miao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
     * 通过验证码登录
     * @param phone 手机号
     */
    @PostMapping("/code")
    public Result sendCode(@RequestParam("phone")String phone){
        return userService.sendCode(phone);
    }


    @PostMapping("/register")
    public Result register(UserRegisterRequest userRegisterRequest){
        String userAccount=userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String stuCode=userRegisterRequest.getStuCode();
        return userService.register(userAccount,userPassword,checkPassword,stuCode);
    }
}
