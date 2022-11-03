package com.miao.service;

import com.miao.DTO.UserDTO;
import com.miao.common.BaseResponse;
import com.miao.domain.User;

import java.util.List;

/**
 * @author Dhx_
 * @className AdminService
 * @description TODO
 * @date 2022/10/24 22:29
 */
public interface AdminService {
    BaseResponse<User> queryInfoById(Long id);

    BaseResponse<List<UserDTO>> list();

    BaseResponse<Long> deleteUserById(Long id);

    BaseResponse<List<User>> listUserBasicInfo();
}
