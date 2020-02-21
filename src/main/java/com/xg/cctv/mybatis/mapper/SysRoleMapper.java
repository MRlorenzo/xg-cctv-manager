package com.xg.cctv.mybatis.mapper;

import com.xg.cctv.mybatis.po.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<SysRole> queryRolesByUserId(@Param("userId") Long userId);

}
