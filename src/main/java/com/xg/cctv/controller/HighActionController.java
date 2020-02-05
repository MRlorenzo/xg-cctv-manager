package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.HighAction;
import com.xg.cctv.service.HighActionService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@RestController
@RequestMapping("/highAction")
public class HighActionController {
    @Autowired
    public HighActionService iHighActionService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param highAction 查询条件
     * @return
     */
    @GetMapping("/page")
    public R getHighActionList(Page<HighAction> page,HighAction highAction){
        return R.ok().put("data" , iHighActionService.selectPage(page, highAction));
    }

    /**
     * 保存和修改公用的
     * @param highAction 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R highActionSave(@RequestBody HighAction highAction){
        boolean rs = iHighActionService.saveOrUpdate(highAction);
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
    public R highActionDelete(@PathVariable String id){
        boolean rs = iHighActionService.removeById(id);
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
        boolean rs = iHighActionService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
