package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@ApiModel(value = "系统用户实体:SysUser", description = "系统用户")
public class SysUser extends Model<SysUser> {


    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 100 , min = 2)
    @ApiModelProperty( value = "用户名" , required=true)
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @Size(max = 100 , min = 6)
    @ApiModelProperty( value = "密码" , required=true)
    private String password;

    /**
     * 状态  0：禁用   1：正常
     */
    @ApiModelProperty( value = "状态  0：禁用   1：正常" , required=true)
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
    @ApiModelProperty( value = "部门id" , required=true)
    private Long departmentId;

    /**
     * 描述
     */
    @ApiModelProperty( value = "描述" , required=true)
    private String description;

    /**
     * 序号
     */
    @ApiModelProperty( value = "序号" , required=true)
    private String no;

    /**
     * 姓名
     */
    @ApiModelProperty( value = "姓名" , required=true)
    private String nickName;

    /**
     * 工号
     */
    @ApiModelProperty( value = "工号" , required=true)
    private String workNo;

    /**
     * 国籍
     */
    @ApiModelProperty( value = "国籍" , required=true)
    private String nationality;

    /**
     * 照片
     */
    @ApiModelProperty( value = "照片" , required=true)
    private String avatar;

    /**
     * 职位
     */
    @ApiModelProperty( value = "职位" , required=true)
    private String position;

    /**
     * 聘用日期
     */
    @ApiModelProperty( value = "聘用日期" , required=true)
    private Date hireDate;

    /**
     * 角色列表（关联sysRole）
     */
    @TableField(exist=false)
    @ApiModelProperty( value = "角色列表（关联sysRole）" , required=true)
    private List<SysRole> roles;

    /**
     * 路由表，也相当于权限列表（关联sysPermission）
     */
    @TableField(exist=false)
    @ApiModelProperty( value = "路由表，也相当于权限列表（关联sysPermission）" , required=true)
    private List<SysPermission> routes;

    /**
     * 部门信息
     */
    @TableField(exist=false)
    @ApiModelProperty( value = "部门信息" , required=true)
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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
