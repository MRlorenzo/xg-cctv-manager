package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
@ApiModel(value="员工表实体:SysStaff", description="员工表")
public class SysStaff extends Model<SysStaff> {


    @TableId(value = "staff_id", type = IdType.AUTO)
    private Long staffId;

    @ApiModelProperty(value = "员工名")
    private String staffName;

    @ApiModelProperty(value = "状态  0：禁用   1：正常")
    private Integer status;

    @ApiModelProperty(value = "工号")
    private String workNo;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;

    @ApiModelProperty(value = "职位id")
    private Long positionId;

    @ApiModelProperty(value = "国籍")
    private String nationality;

    @ApiModelProperty(value = "照片")
    private String avatar;

    @ApiModelProperty(value = "聘用日期")
    private Date hireDate;

    /**
     * 部门信息
     */
    @TableField(exist=false)
    @ApiModelProperty( value = "部门信息" )
    private SysDepartment department;

    /**
     * 职位信息
     */
    @TableField(exist=false)
    @ApiModelProperty( value = "职位信息" )
    private SysPosition position;


    public SysDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SysDepartment department) {
        this.department = department;
    }

    public SysPosition getPosition() {
        return position;
    }

    public void setPosition(SysPosition position) {
        this.position = position;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.staffId;
    }

    @Override
    public String toString() {
        return "SysStaff{" +
        "staffId=" + staffId +
        ", staffName=" + staffName +
        ", status=" + status +
        ", workNo=" + workNo +
        ", departmentId=" + departmentId +
        ", positionId=" + positionId +
        ", nationality=" + nationality +
        ", avatar=" + avatar +
        ", hireDate=" + hireDate +
        "}";
    }
}
