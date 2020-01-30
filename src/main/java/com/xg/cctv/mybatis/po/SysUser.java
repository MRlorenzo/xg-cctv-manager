package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public class SysUser extends Model<SysUser> {


    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 描述
     */
    private String description;

    /**
     * 序号
     */
    private String no;

    /**
     * 姓名
     */
    private String nickName;

    /**
     * 工号
     */
    private String workNo;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 照片
     */
    private String avatar;

    /**
     * 职位
     */
    private String position;

    /**
     * 聘用日期
     */
    private Date hireDate;

    /**
     * 角色列表（关联sysRole）
     */
    @TableField(exist=false)
    private List<SysRole> roles;

    /**
     * 路由表，也相当于权限列表（关联sysPermission）
     */
    @TableField(exist=false)
    private List<SysPermission> routes;

    /**
     * 部门信息
     */
    @TableField(exist=false)
    private SysDepartment department;

    public SysDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SysDepartment department) {
        this.department = department;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public List<SysPermission> getRoutes() {
        return routes;
    }

    public void setRoutes(List<SysPermission> routes) {
        this.routes = routes;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "userId=" + userId +
        ", username=" + username +
        ", password=" + password +
        ", status=" + status +
        ", createUserId=" + createUserId +
        ", createTime=" + createTime +
        ", departmentId=" + departmentId +
        ", description=" + description +
        "}";
    }
}
