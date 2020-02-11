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
 * @since 2020-02-11
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
     * 创建时间
     */
    private Date createDate;

    /**
     * 异常信息
     */
    private String stack;

    private String url;

    private String message;

    private String info;


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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        ", createDate=" + createDate +
        ", stack=" + stack +
        ", url=" + url +
        ", message=" + message +
        ", info=" + info +
        "}";
    }
}
