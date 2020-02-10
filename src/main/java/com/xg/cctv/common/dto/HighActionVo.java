package com.xg.cctv.common.dto;

import com.xg.cctv.mybatis.po.HighAction;

public class HighActionVo extends HighAction{
    /********************************员工相关字段*********************************/
    private String username;
    /********************************员工相关字段*********************************/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
