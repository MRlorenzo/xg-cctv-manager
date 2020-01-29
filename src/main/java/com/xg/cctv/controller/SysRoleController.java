package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysRole;
import com.xg.cctv.service.SysRoleService;
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
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    public SysRoleService iSysRoleService;

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/list")
    public R getList(){
        return R.ok().put("data" , iSysRoleService.selectList(null));
    }

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysRole 查询条件
     * @return
     */
    @GetMapping("/page")
    public R getSysRoleList(Page<SysRole> page,SysRole sysRole){
        return R.ok().put("data" , iSysRoleService.selectPage(page, sysRole));
    }

    /**
     * 保存和修改公用的
     * @param sysRole 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R sysRoleSave(@RequestBody SysRole sysRole){
        boolean rs = iSysRoleService.saveOrUpdate(sysRole);
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
    public R sysRoleDelete(@PathVariable String id){
        boolean rs = iSysRoleService.removeById(id);
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
        boolean rs = iSysRoleService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
