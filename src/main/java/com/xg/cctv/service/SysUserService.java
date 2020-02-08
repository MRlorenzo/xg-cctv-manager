package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.SysUserVo;
import com.xg.cctv.common.dto.VoService;
import com.xg.cctv.mybatis.po.SysPermission;
import com.xg.cctv.mybatis.po.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysUserService extends IService<SysUser>, VoService<SysUserVo> {

    /**
     * 分页查询
     * @param page
     * @param sysUser
     * @return
     */
    IPage<SysUser> selectPage(Page<SysUser> page,SysUser sysUser);

    IPage<SysUser> selectPage(Page<SysUser> page,Map<String , Object> params);

    /**
     * 查询所有
     * @param sysUser
     */
    List<SysUser> selectList(SysUser sysUser);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);

    /**
     * 根据用户id查询权限列表
     * @param userId
     * @return
     */
    List<SysPermission> selectPermissionsByUserId(Long userId);

    Set<String> permissions2set(List<SysPermission> sysPermissions);
}
