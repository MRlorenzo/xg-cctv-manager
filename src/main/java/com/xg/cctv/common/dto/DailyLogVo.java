package com.xg.cctv.common.dto;

import com.xg.cctv.mybatis.po.DailyLog;

public class DailyLogVo extends DailyLog{

    /********************************部门相关字段*********************************/
    /**
     * 部门中文名称
     */
    private String name;


    /**
     * 部门编号
     */
    private String departmentCode;
    /********************************部门相关字段*********************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
