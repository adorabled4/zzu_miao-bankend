package com.miao.DTO;

import com.miao.common.BaseResponse;
import lombok.Data;
import java.util.Date;

/**
 * @author Dhx_
 * @className UserUpdateDTO  用户编辑资料DTO
 * @description TODO
 * @date 2022/10/24 21:51
 */
@Data
public class UserInfoDTO {
    private String userName;
    private Integer gender ;
    private String phone;
    private String email;
    private String school;
    private String stuCode;
    private String avatarUrl;
    private Date birth;
}
