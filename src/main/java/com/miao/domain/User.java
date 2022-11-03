package com.miao.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("用户表")
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String userName;

    /**
     * 账户
     */
    @ApiModelProperty(value = "账户名")
    private String userAccount;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "密码",hidden = true)
    private String userPassword;


    /**
     * 学校
     */
    @ApiModelProperty(value = "学校")
    private String school;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像url")
    private String avatarUrl;

    /**
     * 性别 1 男 0 女
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;


    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间",hidden = true)
    private Date createTime;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "生日")
    private Date birth;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",hidden = true)
    private Date updateTime;

    /**
     * 是否是管理员
     */
    @ApiModelProperty(value = "权限",hidden = true)
    private int isAdmin;
    /**
     * 是否注销 1 注销
     */
    @TableLogic
    @ApiModelProperty(value = "逻辑删除",hidden = true)
    private int isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getIsAdmin() == user.getIsAdmin() && Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getUserAccount(), user.getUserAccount()) && Objects.equals(getUserPassword(), user.getUserPassword())  && Objects.equals(getSchool(), user.getSchool()) && Objects.equals(getAvatarUrl(), user.getAvatarUrl()) && Objects.equals(getGender(), user.getGender()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getEmail(), user.getEmail())  && Objects.equals(getCreateTime(), user.getCreateTime()) && Objects.equals(getUpdateTime(), user.getUpdateTime()) && Objects.equals(getIsDelete(), user.getIsDelete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getUserAccount(), getUserPassword(), getSchool(), getAvatarUrl(), getGender(), getPhone(), getEmail(), getCreateTime(), getUpdateTime(), getIsAdmin(), getIsDelete());
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
        sb.append(", school=").append(school);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", gender=").append(gender);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}