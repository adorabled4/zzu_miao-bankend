package com.miao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账户
     */
    private String userAccount;

    /**
     * 登录密码
     */
    private String userPassword;

    /**
     * 学号
     */
    private String stuCode;

    /**
     * 学校
     */
    private String school;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 性别 1 男 0 女
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private Integer userStatus;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否是管理员
     */
    private int isAdmin;
    /**
     * 是否注销 1 注销
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getIsAdmin() == user.getIsAdmin() && Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getUserAccount(), user.getUserAccount()) && Objects.equals(getUserPassword(), user.getUserPassword()) && Objects.equals(getStuCode(), user.getStuCode()) && Objects.equals(getSchool(), user.getSchool()) && Objects.equals(getAvatarUrl(), user.getAvatarUrl()) && Objects.equals(getGender(), user.getGender()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getUserStatus(), user.getUserStatus()) && Objects.equals(getCreateTime(), user.getCreateTime()) && Objects.equals(getUpdateTime(), user.getUpdateTime()) && Objects.equals(getIsDelete(), user.getIsDelete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getUserAccount(), getUserPassword(), getStuCode(), getSchool(), getAvatarUrl(), getGender(), getPhone(), getEmail(), getUserStatus(), getCreateTime(), getUpdateTime(), getIsAdmin(), getIsDelete());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", stuCode=").append(stuCode);
        sb.append(", school=").append(school);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", gender=").append(gender);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}