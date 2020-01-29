package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @GetMapping("/getSysRolePermissionPageList")
    public R getSysRolePermissionList(Page<SysRolePermission> page,SysRolePermission sysRolePermission){
        return R.ok().put("data" , iSysRolePermissionService.selectPage(page, sysRolePermission));
    }

    /**
     * 保存和修改公用的
     * @param sysRolePermission 传递的实体
     * @return R
     */
    @PostMapping("/sysRolePermissionSave")
    public R sysRolePermissionSave(@RequestBody SysRolePermission sysRolePermission){
        boolean rs = iSysRolePermissionService.saveOrUpdate(sysRolePermission);
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
    @PostMapping("/sysRolePermissionDelete/{id}")
    public R sysRolePermissionDelete(@PathVariable String id){
        boolean rs = iSysRolePermissionService.removeById(id);
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
    @PostMapping("/sysRolePermissionBatchDelete")
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysRolePermissionService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
