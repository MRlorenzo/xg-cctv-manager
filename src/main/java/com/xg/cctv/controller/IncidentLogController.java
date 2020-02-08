package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.common.util.ShiroUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.IncidentLog;
import com.xg.cctv.service.IncidentLogService;
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
@RequestMapping("/incidentLog")
public class IncidentLogController {
    @Autowired
    public IncidentLogService iIncidentLogService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param incidentLog 查询条件
     * @return
     */
    /*@GetMapping("/page")
    public R getIncidentLogList(Page<IncidentLog> page,IncidentLog incidentLog){
        return R.ok().put("data" , iIncidentLogService.selectPage(page, incidentLog));
    }*/
    @GetMapping("/page")
    public R getIncidentLogList(Page<IncidentLogVo> page, Map<String , Object> incidentLog){
        return R.ok().put("data" , iIncidentLogService.selectVoPage(page, incidentLog));
    }

    /**
     * 保存和修改公用的
     * @param incidentLog 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R incidentLogSave(@RequestBody @Valid IncidentLog incidentLog){
        if (incidentLog.getId() == null){
            incidentLog.setCreateUid(ShiroUtils.getUserId());
            incidentLog.setCreateTime(new Date());
        }
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
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iIncidentLogService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
