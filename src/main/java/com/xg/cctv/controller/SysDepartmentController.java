package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysDepartment;
import com.xg.cctv.service.SysDepartmentService;
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
@RequestMapping("/sysDepartment")
public class SysDepartmentController {
    @Autowired
    public SysDepartmentService iSysDepartmentService;

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping("/list")
    public R getList(){
        return R.ok().put("data" , iSysDepartmentService.selectList(null));
    }

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysDepartment 查询条件
     * @return
     */
    @GetMapping("/page")
    public R getSysDepartmentList(Page<SysDepartment> page,SysDepartment sysDepartment){
        return R.ok().put("data" , iSysDepartmentService.selectPage(page, sysDepartment));
    }

    /**
     * 保存和修改公用的
     * @param sysDepartment 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R sysDepartmentSave(@RequestBody SysDepartment sysDepartment){
        boolean rs = iSysDepartmentService.saveOrUpdate(sysDepartment);
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
    public R sysDepartmentDelete(@PathVariable String id){
        boolean rs = iSysDepartmentService.removeById(id);
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
        boolean rs = iSysDepartmentService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
