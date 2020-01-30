package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.TitleListItem;
import com.xg.cctv.service.TitleListItemService;
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
@RequestMapping("/titleListItem")
public class TitleListItemController {
    @Autowired
    public TitleListItemService iTitleListItemService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param titleListItem 查询条件
     * @return
     */
    @GetMapping("/page")
    public R getTitleListItemList(Page<TitleListItem> page,TitleListItem titleListItem){
        return R.ok().put("data" , iTitleListItemService.selectPage(page, titleListItem));
    }

    /**
     * 保存和修改公用的
     * @param titleListItem 传递的实体
     * @return R
     */
    @PostMapping("/save")
    public R titleListItemSave(@RequestBody TitleListItem titleListItem){
        boolean rs = iTitleListItemService.saveOrUpdate(titleListItem);
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
    public R titleListItemDelete(@PathVariable String id){
        boolean rs = iTitleListItemService.removeById(id);
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
        boolean rs = iTitleListItemService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
