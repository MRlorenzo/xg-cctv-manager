package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 路由(权限)表
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@ApiModel(value = "路由(权限)表实体:SysPermission", description = "路由(权限)表")
public class SysPermission extends Model<SysPermission> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 请求路径
     */
    @ApiModelProperty( value = "请求路径" , required=true)
    private String path;

    /**
     * 父id
     */
    @ApiModelProperty( value = "父id" , required=true)
    private Long pid;

    /**
     * 是否删除了
     */
    @ApiModelProperty( value = "是否删除了 0 否 1 是" , required=true)
    private Integer isDel;


    /**
     * 权限字符串
     */
    @ApiModelProperty( value = "权限字符串" , required=false)
    private String perms;

    /**
     * 描述
     */
    @ApiModelProperty( value = "描述" , required=true)
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        "id=" + id +
        ", path=" + path +
        ", pid=" + pid +
        ", isDel=" + isDel +
        ", perms=" + perms +
        ", description=" + description +
        "}";
    }
}
