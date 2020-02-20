package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysPermission;
import com.xg.cctv.service.SysPermissionService;
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
@Api(value = "SysPermissionController", description = "权限相关")
@RequestMapping("/sysPermission")
public class SysPermissionController {
    @Autowired
    public SysPermissionService iSysPermissionService;

    @GetMapping("/root")
    @RequiresPermissions("sysPermission:list")
    @ApiOperation(value="查询一级路由列表", notes="查询一级路由列表接口" , httpMethod = "GET" , response = R.class)
    public R root(){
        SysPermission sysPermission = new SysPermission();
        sysPermission.setPid(0L);

        return R.ok().put("data" , iSysPermissionService.selectList(sysPermission));
    }

    @GetMapping("/find/pid/{pid}")
    @RequiresPermissions("sysPermission:list")
    @ApiOperation(value="根据pid查询路由(权限)列表", notes="根据pid查询路由(权限)列表接口" , httpMethod = "GET" , response = R.class)
    public R findByPid(@PathVariable("pid") Long pid){
        SysPermission sysPermission = new SysPermission();
        sysPermission.setPid(pid);

        return R.ok().put("data" , iSysPermissionService.selectList(sysPermission));
    }

    @GetMapping("/find/roleId/{roleId}")
    @RequiresPermissions("sysPermission:list")
    @ApiOperation(value="根据角色id查询路由(权限)列表", notes="根据角色id查询路由(权限)列表接口" , httpMethod = "GET" , response = R.class)
    public R findByRoleId(@PathVariable("roleId") Long roleId){
        return R.ok().put("data" , iSysPermissionService.selectPermissionByRoleId(roleId));
    }

    @GetMapping("/routes")
    @RequiresPermissions("sysPermission:list")
    @ApiOperation(value="查询所有路由(权限)列表", notes="查询所有路由(权限)列表接口" , httpMethod = "GET" , response = R.class)
    public R getRoutes(){
        return R.ok().put("data" , iSysPermissionService.selectList(null));
    }

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysPermission 查询条件
     * @return
     */
    @GetMapping("/page")
    @RequiresPermissions("sysPermission:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getSysPermissionList(Page<SysPermission> page,SysPermission sysPermission){
        return R.ok().put("data" , iSysPermissionService.selectPage(page, sysPermission));
    }

    /**
     * 保存和修改公用的
     * @param sysPermission 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
    @RequiresPermissions("sysPermission:save")
    public R sysPermissionSave(@RequestBody SysPermission sysPermission){
        boolean rs = iSysPermissionService.saveOrUpdate(sysPermission);
        if (rs){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 根据id删除对象
     * @param id  实体ID
     * @return R
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sysPermission:delete")
    public R sysPermissionDelete(@PathVariable String id){
        boolean rs = iSysPermissionService.removeById(id);
        if (rs) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 批量删除对象
     * @param requestMap 实体集合ID
     * @return R
     */
    @PostMapping("/batchDelete")
    @RequiresPermissions("sysPermission:delete")
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysPermissionService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }

}
