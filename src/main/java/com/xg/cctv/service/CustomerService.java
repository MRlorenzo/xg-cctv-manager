package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 玩家清单表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-31
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 分页查询
     * @param page
     * @param customer
     * @return
     */
    IPage<Customer> selectPage(Page<Customer> page,Customer customer);

    IPage<Customer> selectPage(Page<Customer> page,Map<String , Object> params);

    /**
     * 查询所有
     * @param customer
     */
    List<Customer> selectList(Customer customer);

    List<Customer> selectList(Map<String , Object> params);
}
