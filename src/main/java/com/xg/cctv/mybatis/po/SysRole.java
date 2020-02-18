package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@ApiModel(value = "角色表实体:SysRole", description = "角色表")
public class SysRole extends Model<SysRole> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty( value = "角色名称" , required=true)
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty( value = "备注" , required=true)
    private String description;

    /**
     * 创建者ID
     */
    @ApiModelProperty( hidden = true ,value = "创建者ID" , required=false)
    private Long createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true ,value = "创建时间" , required=false)
    private Date createTime;

    /**
     * 是否已删除 0否 1是
     */
    @ApiModelProperty( value = "是否已删除 0否 1是" , required=false)
    private Integer isDel;


    /**
     * 排序
     */
    @ApiModelProperty( value = "排序" , required=false)
    private Integer sort;


    /**
     * 该角色对应的权限列表
     */
    @TableField(exist=false)
    private List<SysPermission> permissions;

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "id=" + id +
        ", name=" + name +
        ", description=" + description +
        ", createUserId=" + createUserId +
        ", createTime=" + createTime +
        ", isDel=" + isDel +
        ", sort=" + sort +
        "}";
    }
}
