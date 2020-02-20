package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@Api(value = "SysDepartmentController", description = "部门相关")
@RequestMapping("/sysDepartment")
public class SysDepartmentController {
    @Autowired
    public SysDepartmentService iSysDepartmentService;

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sysDepartment:list")
    @ApiOperation(value="查询所有信息", notes="查询信息接口" , httpMethod = "GET" , response = R.class)
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
    @RequiresPermissions("sysDepartment:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getSysDepartmentList(Page<SysDepartment> page,SysDepartment sysDepartment){
        return R.ok().put("data" , iSysDepartmentService.selectPage(page, sysDepartment));
    }

    /**
     * 保存和修改公用的
     * @param sysDepartment 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @RequiresPermissions("sysDepartment:save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
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
    @RequiresPermissions("sysDepartment:delete")
    @ApiImplicitParam(name = "id", value = "id", required = true )
    @ApiOperation(value="根据id删除", notes="根据id删除接口" , httpMethod = "POST" , response = R.class)
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
    @RequiresPermissions("sysDepartment:delete")
    @ApiImplicitParam(name = "ids", value = "ids", required = true )
    @ApiOperation(value="批量删除", notes="批量删除接口" , httpMethod = "POST" , response = R.class)
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysDepartmentService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
