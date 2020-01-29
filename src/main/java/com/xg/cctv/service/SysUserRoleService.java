package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 分页查询
     * @param page
     * @param sysUserRole
     * @return
     */
    IPage<SysUserRole> selectPage(Page<SysUserRole> page,SysUserRole sysUserRole);

    /**
     * 查询所有
     * @param sysUserRole
     */
    List<SysUserRole> selectList(SysUserRole sysUserRole);
}
