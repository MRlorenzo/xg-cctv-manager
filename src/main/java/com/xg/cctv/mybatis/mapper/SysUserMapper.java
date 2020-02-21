package com.xg.cctv.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.SysUserVo;
import com.xg.cctv.common.dto.VoMapper;
import com.xg.cctv.mybatis.po.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysUserMapper extends BaseMapper<SysUser>, VoMapper<SysUserVo> {

    /**
     * 根据用户名查询实体
     * @param username
     * @return
     */
    SysUser queryByUserName(@Param("username") String username);

    IPage<SysUser> selectAllInfoPage(Page<SysUser> page, @Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);

}
