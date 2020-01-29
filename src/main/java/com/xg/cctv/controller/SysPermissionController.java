package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/sysPermission")
public class SysPermissionController {
    @Autowired
    public SysPermissionService iSysPermissionService;

    @GetMapping("/root")
    public R root(){
        SysPermission sysPermission = new SysPermission();
        sysPermission.setPid(0L);

        return R.ok().put("data" , iSysPermissionService.selectList(sysPermission));
    }

    @GetMapping("/find/roleId/{roleId}")
    public R findByRoleId(@PathVariable("roleId") Long roleId){
        return R.ok().put("data" , iSysPermissionService.selectPermissionByRoleId(roleId));
    }

    @GetMapping("/routes")
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
    public R getSysPermissionList(Page<SysPermission> page,SysPermission sysPermission){
        return R.ok().put("data" , iSysPermissionService.selectPage(page, sysPermission));
    }

    /**
     * 保存和修改公用的
     * @param sysPermission 传递的实体
     * @return R
     */
    @PostMapping("/save")
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
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysPermissionService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
