package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.SysUserVo;
import com.xg.cctv.mybatis.mapper.SysPermissionMapper;
import com.xg.cctv.mybatis.po.SysPermission;
import com.xg.cctv.mybatis.po.SysRole;
import com.xg.cctv.mybatis.po.SysUser;
import com.xg.cctv.mybatis.mapper.SysUserMapper;
import com.xg.cctv.mybatis.po.SysUserRole;
import com.xg.cctv.service.SysUserRoleService;
import com.xg.cctv.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public boolean saveOrUpdate(SysUser entity) {
        boolean isUpdate = entity.getUserId() != null;
        entity.setCreateTime(new Date());
        boolean b = super.saveOrUpdate(entity);
        List<SysRole> roles = entity.getRoles();

        // 更新账号与角色关联表
        if (isUpdate){
            // 删除原关联
            Map<String , Object> param = new HashMap<>();
            param.put("user_id" , entity.getUserId());
            sysUserRoleService.removeByMap(param);
        }

        if (roles != null && !roles.isEmpty()){
            // 保存映射关系
            sysUserRoleService.saveBatch(map2UserRole(entity.getUserId() , roles));
        }

        return b;
    }

    private List<SysUserRole> map2UserRole(Long userId , List<SysRole> roles){
        List<SysUserRole> userRoles = new ArrayList<>();
        for (SysRole role : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(role.getId());
            userRoles.add(ur);
        }
        return userRoles;
    }

    @Override
    public SysUser selectByUsername(String username) {
        return sysUserMapper.queryByUserName(username);
    }

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> page, SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        getQueryWrapper(queryWrapper,sysUser);
        return sysUserMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<SysUserVo> selectVoPage(Page<SysUserVo> page , Map<String , Object> params) {
        QueryWrapper<SysUserVo> queryWrapper = new QueryWrapper<>();
        if (params != null){
            if (params.get("userId") != null){
                queryWrapper.eq("user_id" , params.get("userId"));
            }
        }
        return sysUserMapper.selectVoPage(page , queryWrapper);
    }

    @Override
    public List<SysUser> selectList(SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        getQueryWrapper(queryWrapper,sysUser);
        return sysUserMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysUser> getQueryWrapper(QueryWrapper<SysUser> queryWrapper,SysUser sysUser){
        //条件拼接
        return queryWrapper;
    }

    @Override
    public List<SysPermission> selectPermissionsByUserId(Long userId) {
        return sysPermissionMapper.queryPermissionByUserId(userId);
    }

    @Override
    public Set<String> permissions2set(List<SysPermission> sysPermissions){
        if (sysPermissions == null || sysPermissions.isEmpty()){
            return Collections.EMPTY_SET;
        }
        Set<String> permsSet = new HashSet<>();
        sysPermissions.forEach(sysPermission -> {
            if (sysPermission != null){
                String perms = sysPermission.getPerms();
                if (StringUtils.isNotEmpty(perms)){
                    String[] permArr = perms.split(",");
                    for (String perm: permArr) {
                        if (StringUtils.isNotEmpty(perm)){
                            permsSet.add(perm);
                        }
                    }
                }
            }
        });
        return permsSet;
    }
}
