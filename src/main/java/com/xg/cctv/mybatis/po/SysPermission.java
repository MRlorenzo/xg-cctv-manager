package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-01-24
 */
public class SysPermission extends Model<SysPermission> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 是否删除了
     */
    private Integer isDel;

    private Integer sort;

    /**
     * 权限字符串
     */
    private String perms;

    /**
     * 描述
     */
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        ", sort=" + sort +
        ", perms=" + perms +
        ", description=" + description +
        "}";
    }
}
