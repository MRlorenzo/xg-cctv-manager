package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 分页查询
     * @param page
     * @param sysPermission
     * @return
     */
    IPage<SysPermission> selectPage(Page<SysPermission> page,SysPermission sysPermission);

    /**
     * 查询所有
     * @param sysPermission
     */
    List<SysPermission> selectList(SysPermission sysPermission);

    /**
     * 根据角色id查询所有的权限列表
     * @param roleId
     * @return
     */
    List<SysPermission> selectPermissionByRoleId(@Param("roleId") Long roleId);
}
