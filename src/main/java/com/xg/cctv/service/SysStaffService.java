package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysStaff;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
public interface SysStaffService extends IService<SysStaff> {

    /**
     * 分页查询
     * @param page
     * @param sysStaff
     * @return
     */
    IPage<SysStaff> selectPage(Page<SysStaff> page, SysStaff sysStaff);

    IPage<SysStaff> selectPage(Page<SysStaff> page, Map<String, Object> params);

    /**
     * 查询所有
     * @param sysStaff
     */
    List<SysStaff> selectList(SysStaff sysStaff);

    List<SysStaff> selectList(Map<String, Object> params);

    IPage<SysStaff> selectAllInfoPage(Page<SysStaff> page, Map<String, Object> params);

    List<SysStaff> selectListByLikeNo(String name);

    List<SysStaff> selectListByWorkNo(String no);
}
