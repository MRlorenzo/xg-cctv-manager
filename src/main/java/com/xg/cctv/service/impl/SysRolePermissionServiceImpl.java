package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysRolePermission;
import com.xg.cctv.mybatis.mapper.SysRolePermissionMapper;
import com.xg.cctv.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@Service
@Transactional
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public IPage<SysRolePermission> selectPage(Page<SysRolePermission> page, SysRolePermission sysRolePermission) {
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<SysRolePermission>();
        getQueryWrapper(queryWrapper,sysRolePermission);
        return sysRolePermissionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysRolePermission> selectList(SysRolePermission sysRolePermission) {
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<SysRolePermission>();
        getQueryWrapper(queryWrapper,sysRolePermission);
        return sysRolePermissionMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysRolePermission> getQueryWrapper(QueryWrapper<SysRolePermission> queryWrapper,SysRolePermission sysRolePermission){
        //条件拼接
    
        return queryWrapper;
    }
}
