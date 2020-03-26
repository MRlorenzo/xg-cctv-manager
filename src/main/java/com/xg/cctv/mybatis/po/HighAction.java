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
 * 西港监控部高额活动表
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@ApiModel(value = "西港监控部高额活动表实体:HighAction", description = "西港监控部高额活动表")
public class HighAction extends Model<HighAction> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 编码
     */
    @NotBlank
    @ApiModelProperty( value = "编码" , required=true)
    private String code;

    /**
     * 币种
     */
    @ApiModelProperty( value = "币种" , required=true)
    private String coinCode;

    /**
     * 金额
     */
    @ApiModelProperty( value = "金额" , required=true)
    private BigDecimal total;

    /**
     * 上下水报告
     */
    @ApiModelProperty( value = "上下水报告" , required=true)
    private String report;

    /**
     * 涉及员工
     */
    @NotNull
    @ApiModelProperty( value = "涉及员工" , required=true)
    private Long involveUid;

    /**
     * 监控部
     */
    @ApiModelProperty( value = "监控部" , required=true)
    private String monitor;

    /**
     * 备注
     */
    @ApiModelProperty( value = "备注" , required=true)
    private String remarks;

    /**
     * 创建人
     */
    @ApiModelProperty( value = "创建人" , required=true)
    private Long createUid;

    /**
     * 创建时间
     */
    @ApiModelProperty( value = "创建时间" , required=true)
    private Date createTime;

    /**
     * 图片路径以','分割
     */
    @ApiModelProperty( value = "图片路径以','分割" , required=false)
    private String urls;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Long getInvolveUid() {
        return involveUid;
    }

    public void setInvolveUid(Long involveUid) {
        this.involveUid = involveUid;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HighAction{" +
        "id=" + id +
        ", date=" + date +
        ", tableCode=" + tableCode +
        ", code=" + code +
        ", coinCode=" + coinCode +
        ", total=" + total +
        ", report=" + report +
        ", involveUid=" + involveUid +
        ", monitor=" + monitor +
        ", remarks=" + remarks +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        ", urls=" + urls +
        "}";
    }
}
