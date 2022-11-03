package com.miao.controller;

import com.miao.common.BaseResponse;
import com.miao.domain.User;
import com.miao.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Dhx_
 * @className AdminController
 * @description TODO
 * @date 2022/10/24 22:28
 */

@Api(value = "用用于管理员管理后台")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    /**
     * 根据id查询特定用户的所有信息
     *
     * @param id
     * @return
     */
    @GetMapping("/queryInfoById/{id}")
    @ApiOperation("根据用户id查看用户详情")
    public BaseResponse<User> queryInfoById(@PathVariable("id") Long id) {
        return adminService.queryInfoById(id);
    }
    /**
     * 展示所有用户
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看用户基本信息列表")
    public BaseResponse<List<User>> list() {
        return adminService.listUserBasicInfo();
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @ApiOperation("根据用户id删除用户")
    @PostMapping("/delete/{id}")
    public BaseResponse<Long> deleteUserById(@PathVariable("id") Long id) {
        return adminService.deleteUserById(id);
    }
}
