package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.mybatis.po.IncidentLog;
import com.xg.cctv.mybatis.mapper.IncidentLogMapper;
import com.xg.cctv.service.IncidentLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 西港监控部事件报告表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@Service
@Transactional
public class IncidentLogServiceImpl extends ServiceImpl<IncidentLogMapper, IncidentLog> implements IncidentLogService {

    @Autowired
    private IncidentLogMapper incidentLogMapper;

    @Override
    public IPage<IncidentLogVo> selectVoPage(Page<IncidentLogVo> page, Map<String, Object> params) {
        QueryWrapper<IncidentLogVo> queryWrapper = new QueryWrapper<>();
        getVoQueryWrapper(queryWrapper , params);
        return incidentLogMapper.selectVoPage(page , queryWrapper);
    }

    @Override
    public List<IncidentLogVo> selectVoList(Map<String, Object> params) {
        QueryWrapper<IncidentLogVo> queryWrapper = new QueryWrapper<>();
        getVoQueryWrapper(queryWrapper , params);
        return incidentLogMapper.selectVoList(queryWrapper);
    }

    @Override
    public QueryWrapper<IncidentLogVo> getVoQueryWrapper(QueryWrapper<IncidentLogVo> queryWrapper, Map<String, Object> params) {
        if (params == null){
            return queryWrapper;
        }
        if (params.get("tableCode") != null){
            queryWrapper.like("table_code" , params.get("tableCode"));
        }

        if (params.get("code") != null){
            queryWrapper.like("code" , params.get("code"));
        }

        if (params.get("coinCode") != null){
            queryWrapper.eq("coin_code" , params.get("coinCode"));
        }

        if (params.get("involveUid") != null){
            queryWrapper.eq("involve_uid", params.get("involveUid"));
        }

        if (params.get("departmentId") != null){
            queryWrapper.eq("department_id" , params.get("departmentId"));
        }

        if (params.get("monitor") != null){
            queryWrapper.eq("monitor" , params.get("monitor"));
        }
        return queryWrapper;
    }

    @Override
    public IPage<IncidentLog> selectPage(Page<IncidentLog> page, IncidentLog incidentLog) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper,incidentLog);
        return incidentLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<IncidentLog> selectPage(Page<IncidentLog> page, Map<String, Object> params) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper , params);
        return incidentLogMapper.selectPage( page, queryWrapper);
    }

    @Override
    public List<IncidentLog> selectList(IncidentLog incidentLog) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper,incidentLog);
        return incidentLogMapper.selectList(queryWrapper);
    }

    @Override
    public List<IncidentLog> selectList(Map<String, Object> params) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper, params);
        return incidentLogMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<IncidentLog> getQueryWrapper(QueryWrapper<IncidentLog> queryWrapper,IncidentLog incidentLog){
        //条件拼接
        if (incidentLog == null){
            return queryWrapper;
        }

        if (incidentLog.getTableCode() != null){
            queryWrapper.like("table_code" , incidentLog.getTableCode());
        }

        if (incidentLog.getCode() != null){
            queryWrapper.like("code" , incidentLog.getCode());
        }

        if (incidentLog.getCoinCode() != null){
            queryWrapper.eq("coin_code" , incidentLog.getCoinCode());
        }

        if (incidentLog.getInvolveUid() != null){
            queryWrapper.eq("involve_uid", incidentLog.getInvolveUid());
        }

        if (incidentLog.getDepartmentId() != null){
            queryWrapper.eq("department_id" , incidentLog.getDepartmentId());
        }

        if (incidentLog.getMonitor() != null){
            queryWrapper.eq("monitor" , incidentLog.getMonitor());
        }

        return queryWrapper;
    }

    public QueryWrapper<IncidentLog> getQueryWrapper(QueryWrapper<IncidentLog> queryWrapper, Map<String , Object> params){
        //条件拼接
        return queryWrapper;
    }
}
