package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.common.dto.VoService;
import com.xg.cctv.mybatis.po.IncidentLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 西港监控部事件报告表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
public interface IncidentLogService extends IService<IncidentLog> ,VoService<IncidentLogVo> {

    /**
     * 分页查询
     * @param page
     * @param incidentLog
     * @return
     */
    IPage<IncidentLog> selectPage(Page<IncidentLog> page,IncidentLog incidentLog);

    IPage<IncidentLog> selectPage(Page<IncidentLog> page,Map<String , Object> params);

    /**
     * 查询所有
     * @param incidentLog
     */
    List<IncidentLog> selectList(IncidentLog incidentLog);
}
