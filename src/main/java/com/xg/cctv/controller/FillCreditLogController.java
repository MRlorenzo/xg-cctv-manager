package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.FillCreditLog;
import com.xg.cctv.service.FillCreditLogService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-01-31
 */
@RestController
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
    public R getFillCreditLogList(Page<FillCreditLog> page,FillCreditLog fillCreditLog){
        return R.ok().put("data" , iFillCreditLogService.selectPage(page, fillCreditLog));
    }

    /**
     * 保存和修改公用的
     * @param fillCreditLog 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R fillCreditLogSave(@RequestBody FillCreditLog fillCreditLog){
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
