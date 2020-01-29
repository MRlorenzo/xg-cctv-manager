package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @GetMapping("/getSysUserRolePageList")
    public R getSysUserRoleList(Page<SysUserRole> page,SysUserRole sysUserRole){
        return R.ok().put("data" , iSysUserRoleService.selectPage(page, sysUserRole));
    }

    /**
     * 保存和修改公用的
     * @param sysUserRole 传递的实体
     * @return R
     */
    @PostMapping("/sysUserRoleSave")
    public R sysUserRoleSave(@RequestBody SysUserRole sysUserRole){
        boolean rs = iSysUserRoleService.saveOrUpdate(sysUserRole);
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
    @PostMapping("/sysUserRoleDelete/{id}")
    public R sysUserRoleDelete(@PathVariable String id){
        boolean rs = iSysUserRoleService.removeById(id);
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
    @PostMapping("/sysUserRoleBatchDelete")
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysUserRoleService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
