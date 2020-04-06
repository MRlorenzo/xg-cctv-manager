package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.excel.impl.IncidentLogExcelService;
import com.xg.cctv.exception.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.IncidentLog;
import com.xg.cctv.service.IncidentLogService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@RestController
@Validated
@Api(value = "IncidentLogController", description = "事件报告")
@RequestMapping("/incidentLog")
public class IncidentLogController {
    @Autowired
    public IncidentLogService iIncidentLogService;

    @Value("${local.fileserver.dir}")
    private String basePath;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param incidentLog 查询条件
     * @return
     */
    @GetMapping("/page")
    @RequiresPermissions("incidentLog:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getIncidentLogList(Page<IncidentLogVo> page,@RequestParam Map<String , Object> incidentLog){
        return R.ok().put("data" , iIncidentLogService.selectAllPage(page, incidentLog));
    }

    @GetMapping("/excel")
    @RequiresPermissions("incidentLog:excel")
    @ApiOperation(value="导出EXCEL", notes="导出EXCEL接口" , httpMethod = "GET" , response = R.class)
    public R getIncidentLogExcel(@RequestParam Map<String , Object> incidentLog) {
        List<IncidentLogVo> incidentLogs = iIncidentLogService.selectVoList(incidentLog);
        String key ;
        if (incidentLog.get("needImg") != null && Boolean.valueOf(incidentLog.get("needImg").toString())){
            try {
                key = new IncidentLogExcelService().exportExcel(basePath , incidentLogs);
            } catch (IOException e) {
                throw new RRException("图片地址错误");
            }
        }else {
            key = new IncidentLogExcelService().exportExcel(incidentLogs);
        }
        return R.ok()
                .put("key" , key);
    }

    /**
     * 保存和修改公用的
     * @param incidentLog 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @RequiresPermissions("incidentLog:save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
    public R incidentLogSave(@RequestBody @Valid IncidentLog incidentLog){
        boolean rs = iIncidentLogService.saveOrUpdate(incidentLog);
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
    @RequiresPermissions("incidentLog:delete")
    @ApiImplicitParam(name = "id", value = "id", required = true )
    @ApiOperation(value="根据id删除", notes="根据id删除接口" , httpMethod = "POST" , response = R.class)
    public R incidentLogDelete(@PathVariable String id){
        boolean rs = iIncidentLogService.removeById(id);
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
    @RequiresPermissions("incidentLog:list")
    @ApiImplicitParam(name = "ids", value = "ids", required = true )
    @ApiOperation(value="批量删除", notes="批量删除接口" , httpMethod = "POST" , response = R.class)
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iIncidentLogService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
