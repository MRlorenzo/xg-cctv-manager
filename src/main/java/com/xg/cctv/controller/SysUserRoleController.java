package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysUserRole;
import com.xg.cctv.service.SysUserRoleService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@RestController
@Api(value = "SysUserRoleController", description = "用户与角色关联")
@RequestMapping("/sysUserRole")
public class SysUserRoleController {
    @Autowired
    public SysUserRoleService iSysUserRoleService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysUserRole 查询条件
     * @return
     */
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getSysUserRoleList(Page<SysUserRole> page,SysUserRole sysUserRole){
        return R.ok().put("data" , iSysUserRoleService.selectPage(page, sysUserRole));
    }

    /**
     * 保存和修改公用的
     * @param sysUserRole 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
    public R sysUserRoleSave(@RequestBody SysUserRole sysUserRole){
        boolean rs = iSysUserRoleService.saveOrUpdate(sysUserRole);
        if (rs){
            return R.ok();
        }
        return R.error();
    }

}
