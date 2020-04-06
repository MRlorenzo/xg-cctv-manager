package com.xg.cctv.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysStaff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xg.cctv.mybatis.po.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 员工表 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
public interface SysStaffMapper extends BaseMapper<SysStaff> {

    /**
     * 分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<SysStaff> selectAllInfoPage(Page<SysStaff> page, @Param(Constants.WRAPPER) Wrapper<SysStaff> queryWrapper);

    /**
     * 根据用户id查询角色列表
     * @param params
     * @return
     */
    List<SysStaff> queryStaffsById( Map<String, Object> params);
}
