package com.xg.cctv.mybatis.mapper;

import com.xg.cctv.mybatis.po.SysDepartment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-24
 */
public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    SysDepartment queryDepartmentById(@Param("id") Long id);

}
