package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.excel.impl.FillCreditLogExcelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.FillCreditLog;
import com.xg.cctv.service.FillCreditLogService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-02-01
 */
@RestController
@Validated
@RequestMapping("/fillCreditLog")
public class FillCreditLogController {
    @Autowired
    public FillCreditLogService iFillCreditLogService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param fillCreditLog 查询条件
     * @return
     */
    @GetMapping("/page")
    public R getFillCreditLogList(Page<FillCreditLog> page,Map<String , Object> fillCreditLog){
        return R.ok().put("data" , iFillCreditLogService.selectPage(page, fillCreditLog));
    }

    @GetMapping("/excel")
    public R getFillCreditLogExcel(Map<String , Object> fillCreditLog){
        List<FillCreditLog> fillCreditLogs = iFillCreditLogService.selectList(fillCreditLog);
        return R.ok()
                .put("key" , new FillCreditLogExcelService().exportExcel(fillCreditLogs));
    }

    /**
     * 保存和修改公用的
     * @param fillCreditLog 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R fillCreditLogSave(@RequestBody @Valid FillCreditLog fillCreditLog){
        if (fillCreditLog.getId() == null){
            fillCreditLog.setCreateUid(ShiroUtils.getUserId());
            fillCreditLog.setCreateTime(new Date());
        }
        boolean rs = iFillCreditLogService.saveOrUpdate(fillCreditLog);
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
    public R fillCreditLogDelete(@PathVariable String id){
        boolean rs = iFillCreditLogService.removeById(id);
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
        boolean rs = iFillCreditLogService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
