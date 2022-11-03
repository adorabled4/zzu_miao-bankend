package com.miao.controller;

import com.miao.DTO.UserDTO;
import com.miao.DTO.UserRegisterDTO;
import com.miao.DTO.UserUpdateDTO;
import com.miao.common.BaseResponse;
import com.miao.domain.User;
import com.miao.service.UserService;
import com.miao.util.ResultUtil;
import com.miao.util.UserHolder;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("发送验证码")
    public BaseResponse<String> sendCode(@RequestParam("phone")String phone){
        return userService.sendCode(phone);
    }

    /**
     * 注册功能
     * @param userRegisterDTO 封装信息到userRegisterDTO
     * @return 返回用户的id
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public BaseResponse<String> register(UserRegisterDTO userRegisterDTO){
        String userAccount=userRegisterDTO.getUserAccount();
        String userPassword = userRegisterDTO.getUserPassword();
        String checkPassword = userRegisterDTO.getCheckPassword();
        return userService.register(userAccount,userPassword,checkPassword);
    }

    /**
     * 普通登录 输入用户名以及密码登录
     * @param userAccount 账户
     * @param userPassword 密码
     * @return 返回登录用户基本信息
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponse<UserDTO> login(@RequestParam("account")String userAccount, @RequestParam("password")String userPassword){
        return userService.login(userAccount,userPassword);
    }

    /**
     * 获取当前的用户并返回
     * @return 返回当前用户基本信息
     */
    @GetMapping("/current")
    @ApiOperation("获取当前登录的用户的基本信息")
    public BaseResponse<UserDTO> getCurrent(){
        return ResultUtil.success(UserHolder.getUser());
    }

    /**
     * 根据id 查询用户信息
     * @param id  用户id
     * @return UserDTO 返回用户基本信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据用户id获取用户基本信息")
    public BaseResponse<UserDTO> queryUserById(@PathVariable("id")Long id){
        return userService.queryUserById(id);
    }

    /**
     * 查询用户详细信息 => 展示用户资料
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @ApiOperation("根据用户id获取用户详细信息")
    public BaseResponse<User> queryUserInfoById(@PathVariable("id")Long id){
        return userService.queryUserInfoById(id);
    }

    /**
     * 根据关键词查询用户
     * @param userName
     * @return
     */
    @GetMapping("/search")
    @ApiOperation("根据关键词查询用户")
    public BaseResponse<List<UserDTO>> searchUsers(@RequestParam("username")String userName){
        return userService.searchUsers(userName);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public BaseResponse<Long> logout(HttpServletRequest request){
        return userService.logout(request);
    }

//    /**
//     * 根据id 删除用户
//     * @param id
//     * @return 返回被删除用户的 id
//     */
//    @PostMapping("/delete")
//    @ApiOperation("退出登录")
//    public  BaseResponse<Long> deleteUserById(@RequestParam("id")Long id){
//        return userService.deleteUserById(id);
//    }

    /**
     * 编辑资料, 不允许编辑 account
     * @param userUpdateDTO  包含了用户编辑信息的参数
     * @return 返回用户的账户
     */
    @PostMapping("/updateUserInfo")
    @ApiOperation("修改用户信息")
    public BaseResponse<String> updateUserInfo(UserUpdateDTO userUpdateDTO){
        return userService.updateUserInfo(userUpdateDTO);
    }
}
