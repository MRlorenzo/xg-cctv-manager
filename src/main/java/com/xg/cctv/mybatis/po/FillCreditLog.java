package com.xg.cctv.mybatis.po;

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
 * 西港监控部日常加彩/缴码表
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-31
 */
public class FillCreditLog extends Model<FillCreditLog> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 1 加彩 2 缴码
     */
    private Integer type;

    /**
     * 序列号
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
     * 币种
     */
    private String coinCode;

    /**
     * 通知人
     */
    private String alerterName;

    /**
     * 监控部
     */
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
        ", no=" + no +
        ", date=" + date +
        ", time=" + time +
        ", tableCode=" + tableCode +
        ", coinCode=" + coinCode +
        ", alerterName=" + alerterName +
        ", monitor=" + monitor +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        "}";
    }
}
