package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.exception.RRException;
import com.xg.cctv.mybatis.po.SysPermission;
import com.xg.cctv.mybatis.po.SysRole;
import com.xg.cctv.mybatis.mapper.SysRoleMapper;
import com.xg.cctv.mybatis.po.SysRolePermission;
import com.xg.cctv.service.SysRolePermissionService;
import com.xg.cctv.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public List<SysRole> queryRolesByUserId(Long userId) {
        return sysRoleMapper.queryRolesByUserId(userId);
    }

    @Override
    public boolean saveOrUpdate(SysRole entity) {
        boolean isUpdate = entity.getId() != null;
        boolean b = super.saveOrUpdate(entity);
        List<SysPermission> permissions = entity.getPermissions();
        // 更新权限与角色的关联表
        if (isUpdate){
            // 删除原关联
            Map<String , Object> param = new HashMap<>();
            param.put("role_id" , entity.getId());
            sysRolePermissionService.removeByMap(param);
        }

        if (permissions != null && !permissions.isEmpty()){
            // 保存映射关系
            sysRolePermissionService.saveBatch(map2RolePermission(entity.getId() , permissions));
        }

        return b;
    }

    private List<SysRolePermission> map2RolePermission(Long roleId , List<SysPermission> permissions){
        List<SysRolePermission> rolePermissions = new ArrayList<>();

        for (SysPermission permission : permissions) {
            SysRolePermission rp = new SysRolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(permission.getId());
            rolePermissions.add(rp);
        }

        return rolePermissions;
    }

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> page, SysRole sysRole) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
        getQueryWrapper(queryWrapper,sysRole);
        return sysRoleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysRole> selectList(SysRole sysRole) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
        getQueryWrapper(queryWrapper,sysRole);
        return sysRoleMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysRole> getQueryWrapper(QueryWrapper<SysRole> queryWrapper,SysRole sysRole){
        //条件拼接
        return queryWrapper;
    }
}
