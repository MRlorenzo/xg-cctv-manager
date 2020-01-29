package com.xg.cctv.mybatis.mapper;

import com.xg.cctv.mybatis.po.SysRole;
import com.xg.cctv.mybatis.po.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询实体
     * @param username
     * @return
     */
    SysUser queryByUserName(@Param("username") String username);

}
