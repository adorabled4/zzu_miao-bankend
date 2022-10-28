package com.miao.controller;

import com.miao.DTO.UserDTO;
import com.miao.common.BaseResponse;
import com.miao.domain.User;
import com.miao.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Dhx_
 * @className AdminController
 * @description TODO
 * @date 2022/10/24 22:28
 */
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
    public BaseResponse<User> queryInfoById(@PathVariable("id") Long id) {
        return adminService.queryInfoById(id);
    }
    /**
     * 展示所有用户
     *
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<UserDTO>> list() {
        return adminService.list();
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public BaseResponse<Long> deleteUserById(@PathVariable("id") Long id) {
        return adminService.deleteUserById(id);
    }
}
