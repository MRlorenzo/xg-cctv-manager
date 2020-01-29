package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysUserRole;
import com.xg.cctv.mybatis.mapper.SysUserRoleMapper;
import com.xg.cctv.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysUserRole> selectPage(Page<SysUserRole> page, SysUserRole sysUserRole) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
        getQueryWrapper(queryWrapper,sysUserRole);
        return sysUserRoleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysUserRole> selectList(SysUserRole sysUserRole) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
        getQueryWrapper(queryWrapper,sysUserRole);
        return sysUserRoleMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysUserRole> getQueryWrapper(QueryWrapper<SysUserRole> queryWrapper,SysUserRole sysUserRole){
        //条件拼接
    
        return queryWrapper;
    }
}
