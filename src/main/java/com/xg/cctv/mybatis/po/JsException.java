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
 * 
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-19
 */
public class JsException extends Model<JsException> {


    /**
     * 异常主键
     */
    @TableId(value = "exception_id", type = IdType.AUTO)
    private Long exceptionId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 行号
     */
    private Integer lineNo;

    /**
     * 列号
     */
    private Integer colNo;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 异常信息
     */
    private String exceptionVal;


    public Long getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Long exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Integer getColNo() {
        return colNo;
    }

    public void setColNo(Integer colNo) {
        this.colNo = colNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExceptionVal() {
        return exceptionVal;
    }

    public void setExceptionVal(String exceptionVal) {
        this.exceptionVal = exceptionVal;
    }

    @Override
    protected Serializable pkVal() {
        return this.exceptionId;
    }

    @Override
    public String toString() {
        return "JsException{" +
        "exceptionId=" + exceptionId +
        ", userName=" + userName +
        ", ip=" + ip +
        ", fileName=" + fileName +
        ", lineNo=" + lineNo +
        ", colNo=" + colNo +
        ", createDate=" + createDate +
        ", exceptionVal=" + exceptionVal +
        "}";
    }
}
