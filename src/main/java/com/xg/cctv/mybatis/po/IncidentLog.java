package com.xg.cctv.mybatis.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;

/**
 * <p>
 * 西港监控部事件报告表
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
public class IncidentLog extends Model<IncidentLog> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Date date;

    /**
     * 台号
     */
    private String tableCode;

    /**
     * 事件编码
     */
    private String code;

    /**
     * 币种
     */
    private String coinCode;

    /**
     * 金额
     */
    private BigDecimal total;

    /**
     * 报告
     */
    private String report;

    /**
     * 涉及员工
     */
    private Long involveUid;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 监控部
     */
    private String monitor;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private Long createUid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 图片路径以','分割
     */
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
        return "IncidentLog{" +
        "id=" + id +
        ", date=" + date +
        ", tableCode=" + tableCode +
        ", code=" + code +
        ", coinCode=" + coinCode +
        ", total=" + total +
        ", report=" + report +
        ", involveUid=" + involveUid +
        ", departmentId=" + departmentId +
        ", monitor=" + monitor +
        ", remarks=" + remarks +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        ", urls=" + urls +
        "}";
    }
}
