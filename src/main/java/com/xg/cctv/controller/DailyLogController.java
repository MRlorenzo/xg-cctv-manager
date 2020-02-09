package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.DailyLogVo;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.excel.impl.DailyLogExcelService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.DailyLog;
import com.xg.cctv.service.DailyLogService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
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
@RequestMapping("/dailyLog")
public class DailyLogController {
    @Autowired
    public DailyLogService iDailyLogService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param dailyLog 查询条件
     * @return
     */
    /*@GetMapping("/page")
    public R getDailyLogList(Page<DailyLog> page,DailyLog dailyLog){
        return R.ok().put("data" , iDailyLogService.selectPage(page, dailyLog));
    }*/

    @GetMapping("/page")
    public R getDailyLogList(Page<DailyLogVo> page, Map<String , Object> dailyLog){
        return R.ok().put("data" , iDailyLogService.selectVoPage(page, dailyLog));
    }

    @GetMapping("/excel")
    public R getDailyLogExcel(Map<String , Object> dailyLog){
        List<DailyLog> dailyLogs = iDailyLogService.selectList(dailyLog);
        return R.ok()
                .put("key", new DailyLogExcelService().exportExcel(dailyLogs));
    }

    /**
     * 保存公用的
     * @param dailyLog 传递的实体
     * @return R
     */
    @PostMapping("/save")
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
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iDailyLogService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
