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
 * 文件上传
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-04
 */
@ApiModel(value = "文件上传实体:SysOss", description = "文件上传")
public class SysOss extends Model<SysOss> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * URL地址
     */
    @ApiModelProperty( value = "" , required=true)
    private String url;

    /**
     * 创建时间
     */
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysOss{" +
        "id=" + id +
        ", url=" + url +
        ", createDate=" + createDate +
        "}";
    }
}
