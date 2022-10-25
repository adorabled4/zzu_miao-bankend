package com.miao.controller;

import com.miao.DTO.UserDTO;
import com.miao.DTO.UserInfoDTO;
import com.miao.DTO.UserRegisterDTO;
import com.miao.common.BaseResponse;
import com.miao.domain.User;
import com.miao.service.UserService;
import com.miao.util.ResultUtil;
import com.miao.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Dhx_
 * @className UserController
 * @description TODO用户控制层
 * @date 2022/10/22 16:20
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    /**
     * 发送验证码
     * @param phone 手机号
     */
    @PostMapping("/code")
    public BaseResponse<String> sendCode(@RequestParam("phone")String phone){
        return userService.sendCode(phone);
    }

    /**
     * 注册功能
     * @param userRegisterDTO 封装信息到userRegisterDTO
     * @return 返回用户的id
     */
    @PostMapping("/register")
    public BaseResponse<Long> register(UserRegisterDTO userRegisterDTO){
        String userAccount=userRegisterDTO.getUserAccount();
        String userPassword = userRegisterDTO.getUserPassword();
        String checkPassword = userRegisterDTO.getCheckPassword();
        String stuCode=userRegisterDTO.getStuCode();
        return userService.register(userAccount,userPassword,checkPassword,stuCode);
    }

    /**
     * 普通登录 输入用户名以及密码登录
     * @param userAccount 账户
     * @param userPassword 密码
     * @return 返回登录用户基本信息
     */
    @PostMapping("/login")
    public BaseResponse<UserDTO> login(@RequestParam("account")String userAccount, @RequestParam("password")String userPassword){
        return userService.login(userAccount,userPassword);
    }

    /**
     * 获取当前的用户并返回
     * @return 返回当前用户基本信息
     */
    @GetMapping("/current")
    public BaseResponse<UserDTO> getCurrent(){
        return ResultUtil.success(UserHolder.getUser());
    }

    /**
     * 根据id 查询用户信息
     * @param id  用户id
     * @return UserDTO 返回用户基本信息
     */
    @GetMapping("/{id}")
    public BaseResponse<UserDTO> queryUserById(@PathVariable("id")Long id){
        return userService.queryUserById(id);
    }

    /**
     * 查询用户详细信息 => 展示用户资料
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public BaseResponse<UserInfoDTO> queryUserInfoById(@PathVariable("id")Long id){
        return userService.queryUserInfoById(id);
    }

    /**
     * 根据关键词查询用户
     * @param userName
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<UserDTO>> searchUsers(@RequestParam("username")String userName){
        return userService.searchUsers(userName);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Long> logout(HttpServletRequest request){
        return userService.logout(request);
    }

    /**
     * 根据id 删除用户
     * @param id
     * @return 返回被删除用户的 id
     */
    @PostMapping("/delete")
    public  BaseResponse<Long> deleteUserById(@RequestParam("id")Long id){
        return userService.deleteUserById(id);
    }

    /**
     * 编辑资料, 不允许编辑 account
     * @param userInfoDTO  包含了用户编辑信息的参数
     * @return 返回用户的账户
     */
    @PostMapping("/updateUserInfo")
    public BaseResponse<String> updateUserInfo(UserInfoDTO userInfoDTO){
        return userService.updateUserInfo(userInfoDTO);
    }
}
