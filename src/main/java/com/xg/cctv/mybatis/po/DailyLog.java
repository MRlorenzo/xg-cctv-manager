package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 西港监控部日志表
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@ApiModel(value = "西港监控部日志表实体:DailyLog", description = "西港监控部日志表")
public class DailyLog extends Model<DailyLog> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 序号
     */
    @NotBlank
    @ApiModelProperty( value = "序号" , required=true)
    private String no;

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
     * 主题
     */
    @ApiModelProperty( value = "主题" , required=true)
    private String subject;

    /**
     * 细节
     */
    @ApiModelProperty( value = "细节" , required=true)
    private String details;

    /**
     * 通知人
     */
    @ApiModelProperty( value = "通知人" , required=true)
    private String alerterName;

    /**
     * 部门ID
     */
    @NotNull
    @ApiModelProperty( value = "部门ID" , required=true)
    private Long departmentId;

    /**
     * 监控部
     */
    @ApiModelProperty( value = "监控部" , required=true)
    private String monitor;

    /**
     * 结论
     */
    @ApiModelProperty( value = "结论" , required=true)
    private String conclusion;

    /**
     * 创建人
     */
    @ApiModelProperty( hidden = true ,value = "创建人" , required=false)
    private Long createUid;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true ,value = "创建时间" , required=false)
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAlerterName() {
        return alerterName;
    }

    public void setAlerterName(String alerterName) {
        this.alerterName = alerterName;
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

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
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
        return "DailyLog{" +
        "id=" + id +
        ", no=" + no +
        ", date=" + date +
        ", time=" + time +
        ", tableCode=" + tableCode +
        ", subject=" + subject +
        ", details=" + details +
        ", alerterName=" + alerterName +
        ", departmentId=" + departmentId +
        ", monitor=" + monitor +
        ", conclusion=" + conclusion +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        ", urls=" + urls +
        "}";
    }
}
