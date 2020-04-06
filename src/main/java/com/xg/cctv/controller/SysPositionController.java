package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.SysPosition;
import com.xg.cctv.service.SysPositionService;
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
@Api(tags = "职位表控制器", value = "SysPosition控制器")
@RestController
@RequestMapping("/sysPosition")
public class SysPositionController {
    @Autowired
    public SysPositionService iSysPositionService;

    /**
     * 查询所有职位
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sysPosition:list")
    @ApiOperation(value="查询所有信息", notes="查询信息接口" , httpMethod = "GET" , response = R.class)
    public R getList(){
        return R.ok().put("data" , iSysPositionService.selectList(null));
    }


    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param sysPosition 查询条件
     * @return
     */
    @ApiOperation(value = "分页查询数据")
    @GetMapping("/page")
    @RequiresPermissions("sysPosition:list")
    public R getSysPositionList(Page<SysPosition> page,@RequestParam Map<String , Object> sysPosition){
        return R.ok().put("data" , iSysPositionService.selectPage(page, sysPosition));
    }

    /**
     * 保存和修改公用的
     * @param sysPosition 传递的实体
     * @return R
     */
    @ApiOperation(value = "保存和修改")
    @PostMapping("/save")
    public R sysPositionSave(@RequestBody SysPosition sysPosition){
        boolean rs = iSysPositionService.saveOrUpdate(sysPosition);
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
    public R sysPositionDelete(@PathVariable String id){
        boolean rs = iSysPositionService.removeById(id);
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
        boolean rs = iSysPositionService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
