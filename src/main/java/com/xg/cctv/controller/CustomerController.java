package com.xg.cctv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.excel.impl.CustomerExcelService;
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
    public R getCustomerList(Page<Customer> page,Map<String , Object> customer){
        return R.ok().put("data" , iCustomerService.selectPage(page, customer));
    }

    @GetMapping("/excel")
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
    public R deleteBatchIds(@RequestBody Map<String,List<String>> requestMap){
        List<String> ids = requestMap.get("ids");
        boolean rs = iCustomerService.removeByIds(ids);
        if (rs){
            return R.ok().put("data" , ids.size());
        }
        return R.error();
    }
}
