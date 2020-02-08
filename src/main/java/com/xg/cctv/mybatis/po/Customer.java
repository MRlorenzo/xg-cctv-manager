package com.xg.cctv.mybatis.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import java.io.Serializable;

/**
 * <p>
 * 玩家清单表
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-31
 */
public class Customer extends Model<Customer> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户名称
     */
    @NotBlank
    private String name;

    /**
     * 卡号
     */
    @NotBlank
    private String cardNumber;

    /**
     * 位置
     */
    private String location;

    /**
     * 平均下注
     */
    @DecimalMin(value = "0")
    private BigDecimal avgBetting;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 照片
     */
    private String photo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createUid;

    /**
     * 创建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getAvgBetting() {
        return avgBetting;
    }

    public void setAvgBetting(BigDecimal avgBetting) {
        this.avgBetting = avgBetting;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Customer{" +
        "id=" + id +
        ", name=" + name +
        ", cardNumber=" + cardNumber +
        ", location=" + location +
        ", avgBetting=" + avgBetting +
        ", nationality:=" + nationality +
        ", photo=" + photo +
        ", status=" + status +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        "}";
    }
}
