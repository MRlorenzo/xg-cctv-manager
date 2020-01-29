package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysPermission;
import com.xg.cctv.mybatis.mapper.SysPermissionMapper;
import com.xg.cctv.service.SysPermissionService;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public IPage<SysPermission> selectPage(Page<SysPermission> page, SysPermission sysPermission) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<SysPermission>();
        getQueryWrapper(queryWrapper,sysPermission);
        return sysPermissionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysPermission> selectList(SysPermission sysPermission) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<SysPermission>();
        getQueryWrapper(queryWrapper,sysPermission);
        return sysPermissionMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysPermission> getQueryWrapper(QueryWrapper<SysPermission> queryWrapper,SysPermission sysPermission){
        if (sysPermission == null){
            return queryWrapper;
        }
        //条件拼接
        if (sysPermission.getPid() != null){
            queryWrapper.eq("pid" , sysPermission.getPid());
        }

    
        return queryWrapper;
    }

    @Override
    public List<SysPermission> selectPermissionByRoleId(Long roleId) {
        return sysPermissionMapper.queryPermissionByRoleId(roleId);
    }
}
