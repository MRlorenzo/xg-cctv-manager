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
 * 部门表
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public class SysDepartment extends Model<SysDepartment> {


    /**
     * 部门id
     */
    @TableId(value = "department_id", type = IdType.AUTO)
    private Long departmentId;

    /**
     * 部门中文名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 部门编号
     */
    private String departmentCode;

    /**
     * 排序字段
     */
    private Integer sort;


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.departmentId;
    }

    @Override
    public String toString() {
        return "SysDepartment{" +
        "departmentId=" + departmentId +
        ", name=" + name +
        ", description=" + description +
        ", departmentCode=" + departmentCode +
        ", sort=" + sort +
        "}";
    }
}
