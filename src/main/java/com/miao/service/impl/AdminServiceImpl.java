package com.miao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miao.DTO.UserDTO;
import com.miao.Exception.BusinessException;
import com.miao.common.BaseResponse;
import com.miao.common.ErrorCode;
import com.miao.domain.User;
import com.miao.mapper.UserMapper;
import com.miao.service.AdminService;
import com.miao.util.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dhx_
 * @className AdminServiceImpl
 * @description TODO
 * @date 2022/10/24 22:29
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    UserMapper userMapper;

    /**
     * 根据id查询用户的所有信息
     * @param id
     * @return
     */
    @Override
    public BaseResponse<User> queryInfoById(Long id) {
        User user = userMapper.selectById(id);
        if(user==null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"用户不存在!");
        }
        return ResultUtil.success(user);
    }

    /**
     * 查询所有用户并转换为userDTO
     * @return
     */
    @Override
    public BaseResponse<List<UserDTO>> list() {
        List<UserDTO> userDTOS = userMapper.selectList(new QueryWrapper<>())
                .stream()
                .map(user -> BeanUtil.copyProperties(user, UserDTO.class)
                ).collect(Collectors.toList());
        return ResultUtil.success(userDTOS);
    }

    /**
     * 根据id删除用户
     * @param userId
     * @return
     */
    @Override
    public BaseResponse<Long> deleteUserById(Long userId) {
        userMapper.deleteById(userId);
        return ResultUtil.success(userId);
    }

    @Override
    public BaseResponse<List<User>> listUserBasicInfo() {
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        List<User> basicUsers = users.stream().map(this::getBasicUser).collect(Collectors.toList());
        return ResultUtil.success(basicUsers);
    }
    private User getBasicUser(User user ){
        User basicUser = new User();
        basicUser.setUserName(user.getUserName());
        basicUser.setAvatarUrl(user.getAvatarUrl());
        basicUser.setCreateTime(user.getCreateTime());
        basicUser.setSchool(user.getSchool());
        return basicUser;
    }
}
