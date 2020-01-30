package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.IncidentLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 西港监控部事件报告表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
public interface IncidentLogService extends IService<IncidentLog> {

    /**
     * 分页查询
     * @param page
     * @param incidentLog
     * @return
     */
    IPage<IncidentLog> selectPage(Page<IncidentLog> page,IncidentLog incidentLog);

    /**
     * 查询所有
     * @param incidentLog
     */
    List<IncidentLog> selectList(IncidentLog incidentLog);
}
