package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysDepartment;
import com.xg.cctv.mybatis.mapper.SysDepartmentMapper;
import com.xg.cctv.service.SysDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@Service
@Transactional
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Override
    public IPage<SysDepartment> selectPage(Page<SysDepartment> page, SysDepartment sysDepartment) {
        QueryWrapper<SysDepartment> queryWrapper = new QueryWrapper<SysDepartment>();
        getQueryWrapper(queryWrapper,sysDepartment);
        return sysDepartmentMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysDepartment> selectList(SysDepartment sysDepartment) {
        QueryWrapper<SysDepartment> queryWrapper = new QueryWrapper<SysDepartment>();
        getQueryWrapper(queryWrapper,sysDepartment);
        return sysDepartmentMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysDepartment> getQueryWrapper(QueryWrapper<SysDepartment> queryWrapper,SysDepartment sysDepartment){
        //条件拼接
    
        return queryWrapper;
    }
}
