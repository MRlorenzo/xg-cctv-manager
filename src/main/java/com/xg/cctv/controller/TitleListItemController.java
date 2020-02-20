package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.TitleListItem;
import com.xg.cctv.service.TitleListItemService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-01-30
 */
@RestController
@Validated
@Api(value = "TitleListItemController", description = "事件主题子项")
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
    @RequiresPermissions("titleListItem:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getTitleListItemList(Page<TitleListItem> page,TitleListItem titleListItem){
        return R.ok().put("data" , iTitleListItemService.selectPage(page, titleListItem));
    }

    @GetMapping("/list")
    @RequiresPermissions("titleListItem:list")
    @ApiOperation(value="查询所有事件主题子项列表", notes="查询所有事件主题子项列表接口" , httpMethod = "GET" , response = R.class)
    public R getTitleListItems(){
        return R.ok().put("data" , iTitleListItemService.selectList(null));
    }

    /**
     * 保存和修改公用的
     * @param titleListItem 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @RequiresPermissions("titleListItem:save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
    public R titleListItemSave(@RequestBody @Valid TitleListItem titleListItem){
        if (titleListItem.getId() == null){
            titleListItem.setCreateUid(ShiroUtils.getUserId());
            titleListItem.setCreateTime(new Date());
        }
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
    @RequiresPermissions("titleListItem:delete")
    @ApiImplicitParam(name = "id", value = "id", required = true )
    @ApiOperation(value="根据id删除", notes="根据id删除接口" , httpMethod = "POST" , response = R.class)
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
    @RequiresPermissions("titleListItem:delete")
    @ApiImplicitParam(name = "ids", value = "ids", required = true )
    @ApiOperation(value="批量删除", notes="批量删除接口" , httpMethod = "POST" , response = R.class)
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iTitleListItemService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
