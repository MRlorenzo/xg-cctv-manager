package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.excel.impl.CustomerExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xg.cctv.mybatis.po.Customer;
import com.xg.cctv.service.CustomerService;
import com.xg.cctv.common.util.R;

import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 * @since 2020-01-31
 */
@RestController
@Validated
@Api(value = "CustomerController", description = "客户相关")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    public CustomerService iCustomerService;

    /**
     * 分页查询数据
     *
     * @param page  分页信息
     * @param customer 查询条件
     * @return
     */
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示条数，默认 10", required = false )
    })
    @ApiOperation(value="获取信息分页", notes="信息分页接口" , httpMethod = "GET" , response = R.class)
    public R getCustomerList(Page<Customer> page,Map<String , Object> customer){
        return R.ok().put("data" , iCustomerService.selectPage(page, customer));
    }

    @GetMapping("/excel")
    @ApiOperation(value="导出EXCEL", notes="导出EXCEL接口" , httpMethod = "GET" , response = R.class)
    public R getCustomerExcel(Map<String , Object> customer){
        List<Customer> customers = iCustomerService.selectList(customer);
        return R.ok()
                .put("key" , new CustomerExcelService().exportExcel(customers));
    }

    /**
     * 保存和修改公用的
     * @param customer 传递的实体
     * @return R
     */
    @PostMapping("/save")
    @ApiOperation(value="保存", notes="保存信息接口" , httpMethod = "POST" , response = R.class)
    public R customerSave(@RequestBody @Valid Customer customer){
        if (customer.getId() == null){
            customer.setCreateUid(ShiroUtils.getUserId());
            customer.setCreateTime(new Date());
        }
        boolean rs = iCustomerService.saveOrUpdate(customer);
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
    @ApiImplicitParam(name = "id", value = "id", required = true )
    @ApiOperation(value="根据id删除", notes="根据id删除接口" , httpMethod = "POST" , response = R.class)
    public R customerDelete(@PathVariable String id){
        boolean rs = iCustomerService.removeById(id);
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
    @ApiImplicitParam(name = "ids", value = "ids", required = true )
    @ApiOperation(value="批量删除", notes="批量删除接口" , httpMethod = "POST" , response = R.class)
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iCustomerService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
