package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysStaff;
import com.xg.cctv.service.SysStaffService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-04-04
 */
@Api(tags = "员工表控制器", value = "SysStaff控制器")
@RestController
@RequestMapping("/sysStaff")
public class SysStaffController {
    @Autowired
    public SysStaffService iSysStaffService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysStaff 查询条件
     * @return
     */
    @ApiOperation(value = "分页查询数据")
    @GetMapping("/page")
    @RequiresPermissions("sysStaff:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    public R getSysStaffList(Page<SysStaff> page,@RequestParam Map<String , Object> sysStaff){
        return R.ok().put("data" , iSysStaffService.selectAllInfoPage(page, sysStaff));
    }

    @GetMapping("/like/{username}")
    @RequiresPermissions("sysStaff:list")
    @ApiImplicitParam(name = "username", value = "用户名", required = false )
    @ApiOperation(value="模糊匹配用户信息，根据用户名", notes="模糊匹配用户信息接口" , httpMethod = "GET" , response = R.class)
    public R like(@PathVariable("username") String username){
        return R.ok().put("data" , iSysStaffService.selectListByLikeName(username));
    }

    /**
     * 保存和修改公用的
     * @param sysStaff 传递的实体
     * @return R
     */
    @ApiOperation(value = "保存和修改")
    @PostMapping("/save")
    public R sysStaffSave(@RequestBody SysStaff sysStaff){
        boolean rs = iSysStaffService.saveOrUpdate(sysStaff);
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
    @ApiOperation(value = "根据id删除对象")
    @PostMapping("/delete/{id}")
    public R sysStaffDelete(@PathVariable String id){
        boolean rs = iSysStaffService.removeById(id);
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
    @ApiOperation(value = "批量删除对象")
    @PostMapping("/batchDelete")
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iSysStaffService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
