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
 * 西港监控部高额活动表
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
public class HighAction extends Model<HighAction> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 序号
     */
    private String no;

    /**
     * 日期
     */
    private Date date;

    /**
     * 时间
     */
    private Date time;

    /**
     * 台号
     */
    private String tableCode;

    /**
     * 编码
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
     * 上下水报告
     */
    private String report;

    /**
     * 涉及员工
     */
    private Long involveUid;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HighAction{" +
        "id=" + id +
        ", no=" + no +
        ", date=" + date +
        ", time=" + time +
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
        "}";
    }
}