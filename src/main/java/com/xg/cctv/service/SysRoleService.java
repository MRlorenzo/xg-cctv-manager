package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询
     * @param page
     * @param sysRole
     * @return
     */
    IPage<SysRole> selectPage(Page<SysRole> page,SysRole sysRole);

    /**
     * 查询所有
     * @param sysRole
     */
    List<SysRole> selectList(SysRole sysRole);

    /**
     * 根据用户id查询角色名单
     * @param userId
     * @return
     */
    List<SysRole> queryRolesByUserId(Long userId);
}
