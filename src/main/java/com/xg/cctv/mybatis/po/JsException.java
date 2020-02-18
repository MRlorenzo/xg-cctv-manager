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

import java.io.Serializable;

/**
 * <p>
 * JS异常
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-11
 */
@ApiModel(value = "JS异常实体:JsException", description = "JS异常")
public class JsException extends Model<JsException> {


    /**
     * 异常主键
     */
    @TableId(value = "exception_id", type = IdType.AUTO)
    private Long exceptionId;

    /**
     * 用户名
     */
    @ApiModelProperty( value = "操作者" , required=true)
    private String userName;

    /**
     * 创建时间
     */
    @ApiModelProperty( value = "创建时间" , required=false)
    private Date createDate;

    /**
     * 异常信息
     */
    @ApiModelProperty( value = "堆栈信息" , required=true)
    private String stack;

    @ApiModelProperty( value = "" , required=true)
    private String url;

    @ApiModelProperty( value = "异常名称" , required=true)
    private String message;

    @ApiModelProperty( value = "vue反馈信息" , required=true)
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
