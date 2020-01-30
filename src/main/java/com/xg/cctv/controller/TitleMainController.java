package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.TitleMain;
import com.xg.cctv.service.TitleMainService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-01-30
 */
@RestController
@RequestMapping("/titleMain")
public class TitleMainController {
    @Autowired
    public TitleMainService iTitleMainService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param titleMain 查询条件
     * @return
     */
    @GetMapping("/page")
    public R getTitleMainList(Page<TitleMain> page,TitleMain titleMain){
        return R.ok().put("data" , iTitleMainService.selectPage(page, titleMain));
    }

    /**
     * 保存和修改公用的
     * @param titleMain 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R titleMainSave(@RequestBody TitleMain titleMain){
        boolean rs = iTitleMainService.saveOrUpdate(titleMain);
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
    public R titleMainDelete(@PathVariable String id){
        boolean rs = iTitleMainService.removeById(id);
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
        boolean rs = iTitleMainService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
