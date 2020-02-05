package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.Customer;
import com.xg.cctv.mybatis.mapper.CustomerMapper;
import com.xg.cctv.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 玩家清单表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-31
 */
@Service
@Transactional
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public IPage<Customer> selectPage(Page<Customer> page, Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>();
        getQueryWrapper(queryWrapper,customer);
        return customerMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Customer> selectList(Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>();
        getQueryWrapper(queryWrapper,customer);
        return customerMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<Customer> getQueryWrapper(QueryWrapper<Customer> queryWrapper,Customer customer){
        // 条件拼接
        if (customer == null){
            return queryWrapper;
        }

        // 模糊匹配卡号
        if (customer.getCardNumber() != null){
            queryWrapper.like("card_number" , customer.getCardNumber());
        }

        // 匹配位置
        if (customer.getLocation() != null){
            queryWrapper.eq("location" , customer.getLocation());
        }

        // 匹配状态
        if (customer.getStatus() != null){
            queryWrapper.eq("status" , customer.getStatus());
        }
    
        return queryWrapper;
    }
}
