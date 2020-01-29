package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 分页查询
     * @param page
     * @param sysRolePermission
     * @return
     */
    IPage<SysRolePermission> selectPage(Page<SysRolePermission> page,SysRolePermission sysRolePermission);

    /**
     * 查询所有
     * @param sysRolePermission
     */
    List<SysRolePermission> selectList(SysRolePermission sysRolePermission);
}
