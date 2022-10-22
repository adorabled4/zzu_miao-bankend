package com.miao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 捐助表
 * @TableName t_donation
 */
@TableName(value ="t_donation")
@Data
public class Donation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long donationId;

    /**
     * 捐助的用户的id
     */
    private Long userId;

    /**
     * 被捐助的动物的id
     */
    private Long animalId;

    /**
     * 捐款金额
     */
    private Double money;

    /**
     * 是否已经落实, 这个字段仅管理员可以操作
     */
    private Integer isDo;

    /**
     * 捐款时间
     */
    private Date createTime;

    /**
     * 数据更新时间
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
        Donation other = (Donation) that;
        return (this.getDonationId() == null ? other.getDonationId() == null : this.getDonationId().equals(other.getDonationId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAnimalId() == null ? other.getAnimalId() == null : this.getAnimalId().equals(other.getAnimalId()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getIsDo() == null ? other.getIsDo() == null : this.getIsDo().equals(other.getIsDo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDonationId() == null) ? 0 : getDonationId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAnimalId() == null) ? 0 : getAnimalId().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getIsDo() == null) ? 0 : getIsDo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", donationId=").append(donationId);
        sb.append(", userId=").append(userId);
        sb.append(", animalId=").append(animalId);
        sb.append(", money=").append(money);
        sb.append(", isDo=").append(isDo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}