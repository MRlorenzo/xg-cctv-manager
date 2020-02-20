package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysRolePermission;
import com.xg.cctv.service.SysRolePermissionService;
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
@Api(value = "SysRolePermissionController", description = "角色与权限关联")
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController {
    @Autowired
    public SysRolePermissionService iSysRolePermissionService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysRolePermission 查询条件
     * @return
     */
    @GetMapping("/page")
    @RequiresPermissions("sysRolePermission:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getSysRolePermissionList(Page<SysRolePermission> page,SysRolePermission sysRolePermission){
        return R.ok().put("data" , iSysRolePermissionService.selectPage(page, sysRolePermission));
    }

    /**
     * 保存和修改公用的
     * @param sysRolePermission 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @RequiresPermissions("sysRolePermission:save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
    public R sysRolePermissionSave(@RequestBody SysRolePermission sysRolePermission){
        boolean rs = iSysRolePermissionService.saveOrUpdate(sysRolePermission);
        if (rs){
            return R.ok();
        }
        return R.error();
    }

}
