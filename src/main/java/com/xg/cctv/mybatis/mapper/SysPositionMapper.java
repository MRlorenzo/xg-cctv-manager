package com.xg.cctv.mybatis.mapper;

import com.xg.cctv.mybatis.po.SysPosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 职位表 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
public interface SysPositionMapper extends BaseMapper<SysPosition> {

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    SysPosition queryPositionById(@Param("id") Long id);
}
