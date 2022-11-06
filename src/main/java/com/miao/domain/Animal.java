package com.miao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_animal
 */
@TableName(value ="t_animal")
@Data
public class Animal implements Serializable {
    /**
     * 动物id
     */
    @TableId(type = IdType.AUTO)
    private Long animalId;

    /**
     * 动物名字
     */
    private String animalName;

    /**
     * 物种
     */
    private String species;

    /**
     * 创建者
     */
    private Long createUserId;
    /**
     * 动物图片
     */
    private String pictures;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 动物介绍
     */
    private String content;
    /**
     * 出现地址
     */
    private String address;

    /**
     * 关注人数
     */
    private Long followCount;

    /**
     * 当前用户是否关注动物
     */
    @TableField(exist = false)
    private Boolean isFollow;

    /**
     * 
     */
    private Date createTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Animal other = (Animal) that;
        return (this.getAnimalId() == null ? other.getAnimalId() == null : this.getAnimalId().equals(other.getAnimalId()))
            && (this.getAnimalName() == null ? other.getAnimalName() == null : this.getAnimalName().equals(other.getAnimalName()))
            && (this.getSpecies() == null ? other.getSpecies() == null : this.getSpecies().equals(other.getSpecies()))
            && (this.getPictures() == null ? other.getPictures() == null : this.getPictures().equals(other.getPictures()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getFollowCount() == null ? other.getFollowCount() == null : this.getFollowCount().equals(other.getFollowCount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAnimalId() == null) ? 0 : getAnimalId().hashCode());
        result = prime * result + ((getAnimalName() == null) ? 0 : getAnimalName().hashCode());
        result = prime * result + ((getSpecies() == null) ? 0 : getSpecies().hashCode());
        result = prime * result + ((getPictures() == null) ? 0 : getPictures().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getFollowCount() == null) ? 0 : getFollowCount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", animalId=").append(animalId);
        sb.append(", animalName=").append(animalName);
        sb.append(", species=").append(species);
        sb.append(", pictures=").append(pictures);
        sb.append(", gender=").append(gender);
        sb.append(", address=").append(address);
        sb.append(", followCount=").append(followCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}