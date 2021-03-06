package com.xg.cctv.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.common.dto.VoMapper;
import com.xg.cctv.mybatis.po.IncidentLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 西港监控部事件报告表 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
public interface IncidentLogMapper extends BaseMapper<IncidentLog>,VoMapper<IncidentLogVo> {

    /**
     * 分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<IncidentLogVo> selectAllPage(Page<IncidentLogVo> page, @Param(Constants.WRAPPER) Wrapper<IncidentLogVo> queryWrapper);

}
