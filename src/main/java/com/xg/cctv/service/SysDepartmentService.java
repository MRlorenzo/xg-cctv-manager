package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysDepartment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysDepartmentService extends IService<SysDepartment> {

    /**
     * 分页查询
     * @param page
     * @param sysDepartment
     * @return
     */
    IPage<SysDepartment> selectPage(Page<SysDepartment> page,SysDepartment sysDepartment);

    /**
     * 查询所有
     * @param sysDepartment
     */
    List<SysDepartment> selectList(SysDepartment sysDepartment);
}
