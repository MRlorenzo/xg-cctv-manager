package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.DailyLogVo;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.excel.impl.DailyLogExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.DailyLog;
import com.xg.cctv.service.DailyLogService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
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
@Api(value = "DailyLogController", description = "监控部日志")
@RequestMapping("/dailyLog")
public class DailyLogController {
    @Autowired
    public DailyLogService iDailyLogService;

    @Value("${local.fileserver.dir}")
    private String basePath;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param dailyLog 查询条件
     * @return
     */
    @GetMapping("/page")
    @RequiresPermissions("dailyLog:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getDailyLogList(Page<DailyLogVo> page, Map<String , Object> dailyLog){
        return R.ok().put("data" , iDailyLogService.selectVoPage(page, dailyLog));
    }

    @GetMapping("/excel")
    @RequiresPermissions("dailyLog:excel")
    @ApiOperation(value="导出EXCEL", notes="导出EXCEL接口" , httpMethod = "GET" , response = R.class)
    public R getDailyLogExcel(Map<String , Object> dailyLog) throws IOException {
        List<DailyLogVo> dailyLogs = iDailyLogService.selectVoList(dailyLog);
        for (DailyLogVo d: dailyLogs) {
            d.initImages(basePath);
        }
        return R.ok()
                .put("key", new DailyLogExcelService().exportExcel(dailyLogs));
    }


    /**
     * 保存公用的
     * @param dailyLog 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @RequiresPermissions("dailyLog:save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
    public R dailyLogSave(@RequestBody @Valid DailyLog dailyLog){
        if (dailyLog.getId() == null){
            dailyLog.setCreateUid(ShiroUtils.getUserId());
            dailyLog.setCreateTime(new Date());
        }
        boolean rs = iDailyLogService.saveOrUpdate(dailyLog);
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
    @RequiresPermissions("dailyLog:delete")
    @ApiImplicitParam(name = "id", value = "id", required = true )
    @ApiOperation(value="根据id删除", notes="根据id删除接口" , httpMethod = "POST" , response = R.class)
    public R dailyLogDelete(@PathVariable String id){
        boolean rs = iDailyLogService.removeById(id);
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
    @RequiresPermissions("dailyLog:delete")
    @ApiImplicitParam(name = "ids", value = "ids", required = true )
    @ApiOperation(value="批量删除", notes="批量删除接口" , httpMethod = "POST" , response = R.class)
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iDailyLogService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
