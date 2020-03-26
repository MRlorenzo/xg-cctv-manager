package com.xg.cctv.mybatis.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 西港监控部日常加彩/缴码表
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-01
 */
@ApiModel(value = "西港监控部日常加彩/缴码表实体:FillCreditLog" , description = "西港监控部日常加彩/缴码表")
public class FillCreditLog extends Model<FillCreditLog> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 1 加彩 2 缴码
     */
    @NotNull
    @ApiModelProperty( value = "1 加彩 2 缴码" , required=true)
    private Integer type;


    /**
     * 日期
     */
    @NotNull
    @ApiModelProperty( value = "日期" , required=true)
    private Date date;

    /**
     * 时间
     */
    @NotBlank
    @ApiModelProperty( value = "时间" , required=true)
    private String time;


    /**
     * 台号
     */
    @NotBlank
    @ApiModelProperty( value = "台号" , required=true)
    private String tableCode;

    /**
     * 金额
     */
    @ApiModelProperty( value = "金额" , required=true)
    private BigDecimal total;

    /**
     * 币种
     */
    @ApiModelProperty( value = "币种" , required=true)
    private String coinCode;

    /**
     * 通知人
     */
    @ApiModelProperty( value = "通知人" , required=true)
    private String alerterName;

    /**
     * 监控部
     */
    @ApiModelProperty( value = "监控部" , required=true)
    private String monitor;

    /**
     * 创建人id
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getAlerterName() {
        return alerterName;
    }

    public void setAlerterName(String alerterName) {
        this.alerterName = alerterName;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
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
        return "FillCreditLog{" +
        "id=" + id +
        ", type=" + type +
        ", date=" + date +
        ", tableCode=" + tableCode +
        ", total=" + total +
        ", coinCode=" + coinCode +
        ", alerterName=" + alerterName +
        ", monitor=" + monitor +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        "}";
    }
}
