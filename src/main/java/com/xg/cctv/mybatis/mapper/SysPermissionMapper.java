package com.xg.cctv.mybatis.mapper;

import com.xg.cctv.mybatis.po.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID查询所有的权限列表(即菜单列表)
     * @param userId
     * @return
     */
    List<SysPermission> queryPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据角色id查询所有的权限列表
     * @param roleId
     * @return
     */
    List<SysPermission> queryPermissionByRoleId(@Param("roleId") Long roleId);

}
